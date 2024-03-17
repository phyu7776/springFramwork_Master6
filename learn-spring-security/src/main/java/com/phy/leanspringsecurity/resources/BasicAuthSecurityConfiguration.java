package com.phy.leanspringsecurity.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {

    Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
        @Override
        public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
            httpSecurityCsrfConfigurer.disable();
        }
    };

    Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicCustomizer = new Customizer<HttpBasicConfigurer<HttpSecurity>>() {
        @Override
        public void customize(HttpBasicConfigurer<HttpSecurity> httpSecurityHttpBasicConfigurer) {

        }
    };
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
          auth.anyRequest().authenticated();
        });
//        http.formLogin();
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(httpBasicCustomizer);


        http.csrf(csrfCustomizer);
        return http.build();
    }
}
