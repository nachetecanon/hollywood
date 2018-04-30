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
public class JavaKeycloakHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  private final JavaAuthenticationService javaAuthenticationService;

  private final String username;

  private final String password;


  @Autowired
  public JavaKeycloakHeaderHttpRequestInterceptor(JavaAuthenticationService javaAuthenticationService,
                                                  @Value("${keycloak.credentials.username}") String username,
                                                  @Value("${keycloak.credentials.password}") String password) {
    this.javaAuthenticationService = javaAuthenticationService;
    this.username = username;
    this.password = password;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                      ClientHttpRequestExecution execution) throws IOException {
    if (!request.getHeaders().containsKey(AUTHORIZATION)) {
      HttpHeaders headers = javaAuthenticationService.getRequestHeaders(username, password);
      request.getHeaders().putAll(headers);
    }
    return execution.execute(request, body);
  }
}
