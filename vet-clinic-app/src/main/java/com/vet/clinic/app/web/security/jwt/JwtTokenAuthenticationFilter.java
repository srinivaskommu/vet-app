package com.vet.clinic.app.web.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtTokenAuthenticationFilter extends GenericFilterBean
{

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider)
  {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException
  {
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    if (req instanceof HttpServletRequest)
    {
      request = (HttpServletRequest) req;
    }
    if (res instanceof HttpServletResponse)
    {
      response = (HttpServletResponse) res;
    }

    if (request != null && isNotLoginFlow(request))
    {
      String token = jwtTokenProvider.resolveToken(request);
      if (token != null && jwtTokenProvider.validateToken(token))
      {
        Authentication auth = jwtTokenProvider.getAuthentication(token);

        if (auth != null)
        {
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
      }
      else
      {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "token expired or missing in the request");
      }

    }
    else
    {
      filterChain.doFilter(req, res);
    }

  }

  private boolean isNotLoginFlow(HttpServletRequest req)
  {
    if (req.getRequestURI().contains("/vsp/login"))
    {

      logger.debug("custom authentication not required");

      return false;

    }

    return true;
  }

}
