package net.pi.platform.hollywood.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Component
public class JavaAuthenticationService {

  private final String grantType = "password";

  private final String authServerUrl;

  private final String realm;

  private final String resource;

  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  public JavaAuthenticationService(@Value("${keycloak.auth-server-url}") String authServerUrl,
                                   @Value("${keycloak.realm}") String realm,
                                   @Value("${keycloak.resource}") String resource) {
    this.authServerUrl = authServerUrl;
    this.realm = realm;
    this.resource = resource;
  }

  private String getServiceUrl() {
    String [] path = new String[] {"realms", realm, "protocol", "openid-connect", "token"};
    return UriComponentsBuilder.fromHttpUrl(authServerUrl).pathSegment(path).build().toString();
  }

  private HttpEntity buildAuthenticationHeaders(String username, String password) {
    MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>() {{
      put("grant_type", asList(grantType));
      put("username", asList(username));
      put("password", asList(password));
      put("client_id", asList(resource));
    }};

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_FORM_URLENCODED);
    return new HttpEntity(body, headers);
  }

  public String getAuthenticationToken(String username, String password) {
    HttpEntity headers = buildAuthenticationHeaders(username, password);
    ResponseEntity response = restTemplate.postForEntity(getServiceUrl(), headers, Map.class);
    Map<String, String> body = (Map<String, String>) response.getBody();
    return body.get("access_token");
  }

  public HttpHeaders getRequestHeaders(String username, String password) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(AUTHORIZATION, format("Bearer %s", getAuthenticationToken(username, password)));
    return headers;
  }
}
