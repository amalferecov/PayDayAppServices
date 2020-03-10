package com.heisenberg.zuulgateway.security;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author heisenberg
 */
@Data
@AllArgsConstructor
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -2431943548695454758L;

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }
}
