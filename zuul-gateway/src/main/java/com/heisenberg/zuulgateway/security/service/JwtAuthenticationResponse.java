package com.heisenberg.zuulgateway.security.service;

import java.io.Serializable;


/**
 * @author heisenberg
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 231222212323423423L;

    private final String token;

    public JwtAuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
