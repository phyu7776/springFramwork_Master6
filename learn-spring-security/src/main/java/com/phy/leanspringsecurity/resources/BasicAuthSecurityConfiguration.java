package com.phy.leanspringsecurity.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class BasicAuthSecurityConfiguration {

    Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
        @Override
        public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
            try {
                httpSecurityCsrfConfigurer.disable()
                        .headers(header -> header.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        var user = User.withUsername("test").password("{noop}dummy").roles("USER").build();
//        var admin = User.withUsername("admin").password("{noop}dummy").roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        var user = User.withUsername("test").password("{noop}dummy").roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}dummy").roles("ADMIN", "USER").build();

        return new JdbcUserDetailsManager(dataSource){{
            createUser(user);
            createUser(admin);
        }};
    }

}
