package io.reflectoring.buckpal.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder password(){
        return new BCryptPasswordEncoder(10);
    }
}
