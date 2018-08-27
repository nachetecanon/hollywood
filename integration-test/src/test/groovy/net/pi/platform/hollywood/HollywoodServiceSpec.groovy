package net.pi.platform.hollywood

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.ChainedHttpConfig
import groovyx.net.http.FromServer
import groovyx.net.http.HttpBuilder
import groovyx.net.http.HttpException
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static java.util.Collections.singletonList
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

@Unroll
class HollywoodServiceSpec extends Specification{
    static final SERVICE_NAME = "hollywood-service"
    static final SERVICE_PORT = 8484

    static final REALM = "test-realm"

    public static final String MONGO_HOST = System.properties["mongo.host"] as String
    public static final int MONGO_PORT = 27017
    public static final String MONGO_USER = "localUser"
    public static final String MONGO_DB = "hollywood"
    public static final String MONGO_PASS = "local"

    @Shared
    HttpBuilder restClient = KeycloakHelper.restClient(SERVICE_NAME)

    MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(singletonList(new ServerAddress(MONGO_HOST, MONGO_PORT)),
            singletonList(MongoCredential.createCredential(MONGO_USER, MONGO_DB, MONGO_PASS.toCharArray()))), MONGO_DB)

    def "Should create a dashboard"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)

        def dashboard = validDashboard()
        when:
        def result = createDashboard(accessToken, dashboard)

        then:
        result.name == dashboard.name
        result.widgets.size() == 1
    }

    def "Should list all dashboards"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)
        def expectedDashboards = (0..4).collect { createDashboard(accessToken, validDashboard()) }

        when:
        def result = get(accessToken, "/dashboards") as List

        then:
        result == expectedDashboards
    }

    def "Should fail to create a dashboard if dashboard id is provided"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)

        when:
        createDashboard(accessToken, validDashboard() + [id: randomId()])

        then:
        def exception = thrown(HttpException)
        exception.statusCode == 400
        exception.body.errorCode == 'invalid.input'
        exception.body.errorMessage == 'You cannot create a dashboard with id'
    }

    def "Should fail to create a dashboard if dashboard is #invalidDashboard"(Map invalidDashboard) {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)

        when:
        createDashboard(accessToken, invalidDashboard)

        then:
        def exception = thrown(HttpException)
        exception.statusCode == 400
        exception.body.errorCode == 'invalid.input'

        where:
        invalidDashboard << [
            validDashboard() + [name:  randomAlphanumeric(4)],
            validDashboard() + [name:  randomAlphanumeric(61)],
            validDashboard() + [widgets: [
                name: null,
                bookmarked: true,
                position: [x: 0, y: 1, rows: 2, cols: 2]
            ]],
            validDashboard() + [widgets: [
                    name: randomAlphanumeric(4),
                    bookmarked: true,
                    position: [x: 0, y: 1, rows: 2, cols: 2]
            ]],
            validDashboard() + [widgets: [
                    name: randomAlphanumeric(61),
                    bookmarked: true,
                    position: [x: 0, y: 1, rows: 2, cols: 2]
            ]]
        ]
    }

    def "Should update a dashboard"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)

        when:
        def dashboard = createDashboard(accessToken, validDashboard())
        def newName = "newName${randomAlphanumeric(10)}"
        updateDashboard(accessToken, dashboard << [name: newName])
        def updatedDashboard = getDashboard(accessToken, dashboard.id)

        then:
        updatedDashboard.name == newName
        updatedDashboard.widgets.size() == 1
    }

    def "Should fail to update a dashboard if it does not exist"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)
        def dashboard = validDashboard()

        when:
        updateDashboard(accessToken, dashboard)

        then:
        def exception = thrown(HttpException)
        exception.statusCode == 404
        exception.body.errorCode == 'uri.not.found'
        exception.body.errorMessage == "Unable to update dashboard: Dashboard with id[${dashboard.id}] not found"
    }

    def "Should delete a dashboard"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)
        def dashboard = createDashboard(accessToken, validDashboard())
        when:
        mongoTemplate.findById(dashboard.id,Map.class,"dashboards")["id"] == dashboard.id
        deleteDashboard(accessToken, dashboard.id)
        then:
        mongoTemplate.findById(dashboard.id,Map.class,"dashboards") == null


    }

    def "Should throw entity not found exception when doesnt exist  a dashboard"() {
        given:
        def accessToken = login("user1", "pass1", SERVICE_NAME, REALM)
        def id = "errorID"
        when:
        deleteDashboard(accessToken, id)
        then:
        def exception = thrown(HttpException)
        exception.statusCode == 404
        exception.body.errorCode == 'uri.not.found'
        exception.body.errorMessage == "Unable to delete dashboard: Dashboard with id[${id}] not found"


    }


    static Map validDashboard() {
        [
                name: randomAlphanumeric(5, 60),
                widgets: [
                        [
                                name: randomAlphanumeric(5, 60),
                                bookmarked: true,
                                visualization: [
                                        someParameter: 1,
                                        position: [x: 0, y: 1, rows: 2, cols: 2]
                                ],
                                app: null,
                                type: null,
                                position: [x: 0, y: 1, rows: 2, cols: 2]
                        ]
                ],
                position: [x: 0, y: 1, rows: 2, cols: 2],
                link: [url: "google.es", type: "URL"],
                image: "data:image/png;base64,iVBOmGSf+9aGTtH",
                bookmarked: false
        ]
    }

    private Map createDashboard(accessToken, Map<String, Object> dashboard) {
        post(accessToken, "/dashboards", dashboard)
    }

    private Map updateDashboard(accessToken, Map<String, Object> dashboard) {
        put(accessToken, "/dashboards/$dashboard.id", dashboard)
    }

    private Map getDashboard(accessToken, id) {
        def list = get(accessToken, "/dashboards") as List
        list.find {it.id == id}
    }

    private void deleteDashboard(accessToken, id) {
        delete(accessToken, "/dashboards/$id")
    }

    def post(accessToken, path, payload) {
        restClient.post {
            request.uri = "http://${SERVICE_NAME}:$SERVICE_PORT$path"
            request.contentType = "application/json"
            request.body = JsonOutput.toJson(payload)
            request.headers['Authorization'] = "Bearer ${accessToken}"
            response.parser("application/json") {
                ChainedHttpConfig chainedHttpConfig, FromServer fromServer ->
                    new JsonSlurper().parse(fromServer.reader)
            }
        } as Map
    }

    def put(accessToken, path, payload) {
        restClient.put {
            request.uri = "http://${SERVICE_NAME}:$SERVICE_PORT$path"
            request.contentType = "application/json"
            request.body = JsonOutput.toJson(payload)
            request.headers['Authorization'] = "Bearer ${accessToken}"
            response.parser("application/json") {
                ChainedHttpConfig chainedHttpConfig, FromServer fromServer ->
                    new JsonSlurper().parse(fromServer.reader)
            }
        } as Map
    }

    def get(accessToken, path) {
        restClient.get {
            request.uri = "http://${SERVICE_NAME}:$SERVICE_PORT$path"
            request.contentType = "application/json"
            request.headers['Authorization'] = "Bearer ${accessToken}"
            response.parser("application/json") {
                ChainedHttpConfig chainedHttpConfig, FromServer fromServer ->
                    new JsonSlurper().parse(fromServer.reader)
            }
        }
    }

    def delete(accessToken, path) {
        restClient.delete {
            request.uri = "http://${SERVICE_NAME}:$SERVICE_PORT$path"
            request.contentType = "application/json"
            request.headers['Authorization'] = "Bearer ${accessToken}"
        }
    }

    def login(String username, String password, String clientId, String realm) {
        restClient.post {
            request.uri = "http://keycloak:${KeycloakHelper.KEYCLOAK_PORT}/auth/realms/${realm}/protocol/openid-connect/token"
            request.contentType = "application/x-www-form-urlencoded"
            request.body = [
                    grant_type: "password",
                    username  : username,
                    password  : password,
                    client_id : clientId
            ]

        }.access_token
    }

    def setupSpec() {
        KeycloakHelper.setUp(new KeycloakHelper.KeycloakConfig(realm: REALM, client: SERVICE_NAME, roles: ["READ"],
                users: [new KeycloakHelper.User(username: "user1", password: "pass1", roles: ["READ"])
                        ,new KeycloakHelper.User(username: "noaccess", password: "pass1", roles: [])]))

    }

    def cleanup() {
        mongoTemplate.getCollection("dashboards").drop()
    }

    static randomId() {
        UUID.randomUUID().toString()
    }
}
