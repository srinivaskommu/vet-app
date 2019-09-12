package com.vet.clinic.app.web.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class JwtProperties {

	private String secretKey = "secret";

  @Value("${token.expiration.in.ms}")
	private long validityInMs; 
}
