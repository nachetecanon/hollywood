package net.pi.platform.hollywood.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.pi.platform.authx.client.service.AuthXService;
import net.pi.platform.common.api.exception.errorcode.APIError;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("authx-integration-test")
public class AuthorizedAccessFilterTest {

  @Value("${server.context-path}")
  private String contextPath;

  @Value("${authx.client.adapter.security-enabled:true}")
  private Boolean securityEnabled;

  @Value("${authx.client.adapter.application-name}")
  private String applicationName;

  @MockBean
  private AuthXService authXService;

  @MockBean
  private RestTemplate restTemplate;

  @MockBean
  private ObjectMapper objectMapper;

  private AuthorizedAccessFilter filter;

  @Before
  public void setUp() throws JsonProcessingException {
    when(objectMapper.writeValueAsString(any())).thenReturn("serialized-text");
    filter = new AuthorizedAccessFilter(objectMapper, authXService, contextPath, securityEnabled, applicationName);
  }

  @Test
  public void test_authorized_access() throws IOException, ServletException {
    // Mocks
    doNothing().when(authXService).authorize(anyString(), anyString(), any(List.class));

    // Given
    HttpServletRequest request = httpServletRequest();
    HttpServletResponse response = mock(HttpServletResponse.class);
    FilterChain filterChain = filterChain();

    // When
    filter.doFilter(request, response, filterChain);

    // Then ---> Happiness

    // And
    verify(filterChain, times(1)).doFilter(request, response);
    verifyNoMoreInteractions(filterChain);

  }

  @Test
  public void test_forbidden_access() throws IOException, ServletException {
    // Mocks
    doThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN))
      .when(authXService).authorize(anyString(), anyString(), any(List.class));

    // Given
    HttpServletRequest request = httpServletRequest();
    HttpServletResponse response = httpServletResponse();
    FilterChain filterChain = mock(FilterChain.class);


    // When
    filter.doFilter(request, response, filterChain);

    // Then
    assertThat(response.getStatus()).isEqualTo(APIError.FORBIDDEN.status().value());

    // And
    verifyZeroInteractions(filterChain);

  }

  @Test
  public void test_unexpected_client_error_exception() throws IOException, ServletException {
    // Mocks
    doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
      .when(authXService).authorize(anyString(), anyString(), any(List.class));

    // Given
    HttpServletRequest request = httpServletRequest();
    HttpServletResponse response = httpServletResponse();
    FilterChain filterChain = mock(FilterChain.class);


    // When
    filter.doFilter(request, response, filterChain);

    // Then
    assertThat(response.getStatus()).isEqualTo(APIError.INTERNAL_ERROR.status().value());

    // And
    verifyZeroInteractions(filterChain);

  }

  private FilterChain filterChain() throws IOException, ServletException {
    FilterChain filterChain = mock(FilterChain.class);
    doNothing().when(filterChain).doFilter(any(ServletRequest.class), any(ServletResponse.class));
    return filterChain;
  }

  private HttpServletRequest httpServletRequest() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestURI()).thenReturn("/" + contextPath + "/hollywood/4");
    KeycloakSecurityContext keycloakSecurityContext = mock(KeycloakSecurityContext.class);
    when(keycloakSecurityContext.getTokenString()).thenReturn("my_secret_token");
    when(request.getAttribute(eq(KeycloakSecurityContext.class.getName()))).thenReturn(keycloakSecurityContext);
    return request;
  }

  private HttpServletResponse httpServletResponse() throws IOException {
    HttpServletResponse response = spy(new MockHttpServletResponse());
    PrintWriter writer = mock(PrintWriter.class);
    doNothing().when(writer).print(anyString());
    doNothing().when(writer).flush();
    when(response.getWriter()).thenReturn(writer);
    return response;
  }
}
