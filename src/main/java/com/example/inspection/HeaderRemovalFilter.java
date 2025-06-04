package com.example.inspection;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
//@Deprecated
public class HeaderRemovalFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HeaderRemovalFilter.class);

  private static final String HEADER_TO_REMOVE = "Negotiate";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    String headerValue = request.getHeader(HEADER_TO_REMOVE);

    if (headerValue != null && headerValue.isEmpty()) {
      LOGGER.info("NEGOTIATE HEADER FOUND WITH EMPTY VALUE. Removing header {}", HEADER_TO_REMOVE);

      // Wrap the request to "remove" the header
      HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {

        @Override
        public String getHeader(String name) {
          if (name.equalsIgnoreCase(HEADER_TO_REMOVE)) {
            return null;
          }
          return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
          if (name.equalsIgnoreCase(HEADER_TO_REMOVE)) {
            return Collections.emptyEnumeration();
          }
          return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
          List<String> names = Collections.list(super.getHeaderNames());
          names.removeIf(name -> name.equalsIgnoreCase(HEADER_TO_REMOVE));
          return Collections.enumeration(names);
        }
      };

      filterChain.doFilter(requestWrapper, response);
    } else {
      filterChain.doFilter(request, response);
    }
  }
}
