
package com.plaincoded.restapi.services.jwt;

import java.io.Serializable;

public class UsernameAndPasswordAuthenticationResponse implements Serializable {

    private final String jwt;

    public UsernameAndPasswordAuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}