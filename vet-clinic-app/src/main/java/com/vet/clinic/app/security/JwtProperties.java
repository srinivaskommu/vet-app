package com.vet.clinic.app.security;

import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@Data
public class JwtProperties {
  
  private String secretKey = "secret";

  //validity in milliseconds
  private long validityInMs = 3600000; // 1h

}
