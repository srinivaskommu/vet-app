package com.vet.clinic.app.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@Configuration
public class VspCorsConfig extends CorsConfiguration
{
  @Value("#{'${access.control.allow.origin}'.split(',')}")
  private List<String> accessControlAllowOrigin;

  @Value("#{'${access.control.exposed.headers}'.split(',')}")
  private List<String> exposedHeaders;

  @Value("#{'${access.control.allowed.methods}'.split(',')}")
  private List<String> allowedMethods;

  @Value("#{'${access.control.allowed.headers}'.split(',')}")
  private List<String> allowedHeaders;

  @Value("${access.control.allow.credentials}")
  private boolean allowCredentials;

  @Value("${access.control.max.age}")
  private Long maxAge;

  @Bean()
  public FilterRegistrationBean corsFilter()
  {
    CorsFilter filter = new CorsFilter(buildCorsConfigurationSource());
    FilterRegistrationBean registerFilter = new FilterRegistrationBean(filter);
    registerFilter.setOrder(0);
    return registerFilter;
  }

  private CorsConfigurationSource buildCorsConfigurationSource()
  {
    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/vsp/**", buildCorsConfiguration());
    return corsSource;
  }

  private CorsConfiguration buildCorsConfiguration()
  {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(accessControlAllowOrigin);
    config.setAllowCredentials(allowCredentials);
    config.setAllowedHeaders(allowedHeaders);
    config.setExposedHeaders(exposedHeaders);
    config.setAllowedMethods(allowedMethods);
    config.setMaxAge(maxAge);
    return config;
  }

}
