package net.pi.platform.hollywood.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.pi.platform.authx.client.service.AuthXService;
import net.pi.platform.common.api.exception.APIException;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.removeFirst;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class AuthorizedAccessFilter implements Filter {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final ObjectMapper objectMapper;

  private final AuthXService authXService;

  private final String contextPath;

  private final Boolean securityEnabled;

  private final String applicationName;

  @Autowired
  public AuthorizedAccessFilter(ObjectMapper objectMapper, AuthXService authXService,
                                @Value("${server.context-path}") String contextPath,
                                @Value("${authx.client.adapter.security-enabled:true}") Boolean securityEnabled,
                                @Value("${authx.client.adapter.application-name}") String applicationName) {
    this.objectMapper = objectMapper;
    this.authXService = authXService;
    this.securityEnabled = securityEnabled;
    this.contextPath = contextPath;
    this.applicationName = applicationName;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    if (securityEnabled) {
      logger.debug("security filter enabled");
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      String userToken = getUserToken(request);
      String resourcePath = getResourcePath(request);
      List<String> allowedOperations = getAllowedOperations(request);
      logger.debug("is authorized present request?");
      try {
        authXService.authorize(userToken, resourcePath, allowedOperations);
        logger.debug("yes, it is authorized");
      } catch (HttpClientErrorException e) {
        handleErrorMessage((HttpServletResponse) servletResponse, request, e);
        return;
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  private void handleErrorMessage(HttpServletResponse response, HttpServletRequest request, HttpClientErrorException e) throws IOException {
    response.setStatus(e.getRawStatusCode());
    response.setContentType(APPLICATION_JSON_VALUE);
    String message;
    if (HttpStatus.FORBIDDEN.value() == e.getRawStatusCode()) {
      message = format("Resource Access identified by path {%s} is Forbidden", request.getRequestURI());
    } else {
      message = "Unable to apply Authorization Filter due to: " + e.getMessage();
    }
    Map<String, String> responseMap = new HashMap<>();
    logger.error(message);
    responseMap.put(APIException.ERROR_CODE, valueOf(e.getRawStatusCode()));
    responseMap.put(APIException.ERROR_MESSAGE, message);

    PrintWriter writer = response.getWriter();
    writer.print(objectMapper.writeValueAsString(responseMap));
    writer.flush();
  }

  private String getUserToken(HttpServletRequest request) {
    KeycloakSecurityContext keycloakSecurityContext =
      (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    return keycloakSecurityContext.getTokenString();
  }

  private String getResourcePath(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    if (requestURI.startsWith(contextPath)) {
      requestURI = removeFirst(requestURI, contextPath);
    }
    return "/" + applicationName + requestURI;
  }

  private List<String> getAllowedOperations(HttpServletRequest request) {
    return asList(request.getMethod());
  }

  @Override
  public void destroy() {}
}
