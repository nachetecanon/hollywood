package net.pi.platform.hollywood.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class AddKeycloakHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  private final AuthenticationService authenticationService;

  private final String username;

  private final String password;


  @Autowired
  public AddKeycloakHeaderHttpRequestInterceptor(AuthenticationService authenticationService,
                                                 @Value("${keycloak.credentials.username}") String username,
                                                 @Value("${keycloak.credentials.password}") String password) {
    this.authenticationService = authenticationService;
    this.username = username;
    this.password = password;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                      ClientHttpRequestExecution execution) throws IOException {
    if (!request.getHeaders().containsKey(AUTHORIZATION)) {
      HttpHeaders headers = authenticationService.getRequestHeaders(username, password);
      request.getHeaders().putAll(headers);
    }
    return execution.execute(request, body);
  }
}
