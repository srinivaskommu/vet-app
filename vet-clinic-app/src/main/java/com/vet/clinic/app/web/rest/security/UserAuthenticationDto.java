package com.vet.clinic.app.web.rest.security;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationDto  implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6986746375915710855L;
    private String username;
    private String password;
}
