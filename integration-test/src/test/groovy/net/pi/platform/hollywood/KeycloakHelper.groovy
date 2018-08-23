package net.pi.platform.hollywood

import groovyx.net.http.ApacheHttpBuilder
import groovyx.net.http.HttpBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.InMemoryDnsResolver
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.*
import org.slf4j.LoggerFactory

import javax.ws.rs.ClientErrorException

class KeycloakHelper {
    static logger = LoggerFactory.getLogger(KeycloakHelper)

    static final KEYCLOAK_PORT = 18080

    static private Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl("http://${System.properties["keycloak.host"]}:$KEYCLOAK_PORT/auth")
            .realm("master")
            .grantType(OAuth2Constants.PASSWORD)
            .clientId("admin-cli")
            .username("admin")
            .password("admin")
            .build()

    static KeycloakHelper instance

    //One instance supports only one realm and one client
    String realmName
    String clientInternalId //Client has clientId and Id (aka internal id)

    static KeycloakHelper realm(String kcRealm) {
        try {
            keycloak.realms().create(new RealmRepresentation(
                    realm: kcRealm,
                    enabled: true
            ))
        } catch (ClientErrorException e) {
            logger.info("Realm $kcRealm already exists, skipping")
        }
        instance = new KeycloakHelper(realmName: kcRealm)
        instance
    }

    static KeycloakHelper setUp(config) {
        realm(config.realm)
                .client(config.client)
                .roles(config.roles)
                .users(config.users)
    }


    KeycloakHelper roles(List<String> roles) {
        def clientResource = keycloak.realms().realm(realmName).clients().get(clientInternalId)
        roles.each {
            def representation = new RoleRepresentation(name: it, description: it)
            try {
                clientResource.roles().create(representation)
            } catch (ClientErrorException e) {
                logger.info("Role $it already exists, skipping")
            }
        }
        instance
    }


    KeycloakHelper client(String clientId) {
        keycloak.realms().realm(realmName).with {
            if (clients().findByClientId(clientId).isEmpty()) {
                clients().create(
                        new ClientRepresentation(
                                id: UUID.randomUUID().toString(),
                                clientId: clientId,
                                name: clientId,
                                enabled: true,
                                publicClient: true,
                                directAccessGrantsEnabled: true
                        )
                )
            }
            clientInternalId = clients().findByClientId(clientId).get(0).id
        }
        instance
    }

    KeycloakHelper users(List<User> users) {
        users.each { u -> user(u) }
        instance
    }

    KeycloakHelper user(User user) {
        def realmResource = keycloak.realms().realm(realmName)
        def clientResource = realmResource.clients().get(clientInternalId)
        def id = clientInternalId
        realmResource.users().with {
            if (search(user.username).isEmpty()) {
                create(new UserRepresentation(
                        id: UUID.randomUUID().toString(),
                        username: user.username,
                        emailVerified: true,
                        enabled: true
                ))
                search(user.username).first().with {
                    get(it.id).with {
                        resetPassword(
                                new CredentialRepresentation(
                                        type: CredentialRepresentation.PASSWORD,
                                        temporary: false,
                                        value: user.password
                                )
                        )
                        roles().clientLevel(id).add(user.roles.collect {
                            clientResource.roles().get(it).toRepresentation()
                        })
                    }
                }
            }
        }
        instance
    }

    static HttpBuilder restClient(service) {
        ApacheHttpBuilder.configure {
            client.clientCustomizer { HttpClientBuilder builder ->
                def resolver = new InMemoryDnsResolver()
                resolver.add("keycloak", InetAddress.getByName(System.properties["${service}.host"] as String))
                resolver.add(service, InetAddress.getByName(System.properties["${service}.host"] as String))
                builder.dnsResolver = resolver
                builder.connectionManager = null
            }
        }
    }

    static class User {
        String username
        String password
        List<String> roles
    }

    static class KeycloakConfig {
        String realm
        String client
        List<String> roles
        List<User> users
    }
}

