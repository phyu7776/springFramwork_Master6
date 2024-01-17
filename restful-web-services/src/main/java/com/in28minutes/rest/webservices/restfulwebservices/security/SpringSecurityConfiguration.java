package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        /*Spring Security Filter Chain 을 재정의 할경우 모든 filterChain 을 재정의 하여야 한다
         * 1. 모든 요청은 반드시 인증이되어야한다
         * 2. 인증이 인가되지않는 요청이 있을시 기본 페이지를 보여주어야한다.
         * 3. FilterChain 중에 CSRF 공격을 막기위한 보안단계인데 이것이 에  POST, PUT 에 영향을 줌
         *    따라서 현재 테스트를 위해 해제
        */

        // 모든 요청은 인증이 되어야한다
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // 인증이 인가되지않는 요청이 있을시 기본 페이지를 보여주어야한다.
        http.httpBasic(withDefaults());

        //FilterChain 중에 CSRF 공격을 막기위한 보안단계인데 이것이 에  POST, PUT 에 영향을 따라서 현재 테스트를 위해 해제
        http.csrf().disable();

        return http.build();
    }
}
