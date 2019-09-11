package com.vet.clinic.app.web.rest.common;

import org.springframework.http.HttpHeaders;

public class Utils {
  
  public static HttpHeaders headers()
  {
    HttpHeaders corsHeaders = new HttpHeaders();
    corsHeaders.set("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    corsHeaders.set("Access-Control-Max-Age", "3600");
    corsHeaders.set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    corsHeaders.set("Access-Control-Expose-Headers", "X-TOTAL_COUNT" );
    corsHeaders.set("Server", "");
    
    return corsHeaders;
  }

}
