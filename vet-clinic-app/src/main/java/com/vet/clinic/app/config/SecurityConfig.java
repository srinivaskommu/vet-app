package com.vet.clinic.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.vet.clinic.app.web.security.jwt.JwtSecurityConfigurer;
import com.vet.clinic.app.web.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JwtTokenProvider jwtTokenProvider;
  
  @Override
  public void configure(WebSecurity web) throws Exception
  {
    web
        .ignoring()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .antMatchers("/h2-console/**");
  }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
      
      if(false) {
        //http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        
        http.authorizeRequests()
        .antMatchers("/veterinarians/**").hasRole("ADMIN")
        .antMatchers("/").permitAll()
        .antMatchers("/h2_console/**").permitAll();

      http.csrf().disable();
      http.headers().frameOptions().disable();
        
      }else {
        
        http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/h2_console/**").permitAll()
            .antMatchers("/veterinarians/**").hasRole("ADMIN")
            .antMatchers("/petOwners/**").permitAll()
            .anyRequest().authenticated()
        .and()
        .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        
        http.headers().frameOptions().disable();
      }

      


        //@formatter:on
    }


}

