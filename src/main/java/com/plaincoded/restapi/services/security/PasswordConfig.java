
package com.plaincoded.restapi.services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    private final static int PASSWORD_ENCODER_STRENGTH = 10;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(PASSWORD_ENCODER_STRENGTH);
    }

    public String encode(String password){

        return passwordEncoder().encode(password);
    }
}
