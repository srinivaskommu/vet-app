package com.vet.clinic.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vet.clinic.app.security.JwtSecurityConfigurer;
import com.vet.clinic.app.security.TokenProvider;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    TokenProvider tokenProvider;
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/pets/**").permitAll()
                .antMatchers("/veterinarians/**").hasRole("ADMIN")
                .antMatchers("/appointments/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(tokenProvider));
      //http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        //@formatter:on
    }


}
