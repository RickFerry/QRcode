package br.com.fatec.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppCconfig {
    @Bean
    BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
