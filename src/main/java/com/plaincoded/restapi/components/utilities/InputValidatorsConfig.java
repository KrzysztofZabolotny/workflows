
package com.plaincoded.restapi.components.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InputValidatorsConfig {

    @Bean
    InputValidators inputValidators(){
        return new InputValidators();
    }
}
