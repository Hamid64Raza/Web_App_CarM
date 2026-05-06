package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private  JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http

    ) throws Exception {

      // h(cd)2
      http.csrf().disable().cors().disable();
        //haap
      //  http.authorizeHttpRequests().anyRequest().permitAll();
       // http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
              .requestMatchers("/api/v1/auth/signup","/api/v1/auth/userLogin","/api/v1/auth/content-manger-signup","/api/v1/auth/blog-manger-signup","/api/v1/auth/login-otp","/api/v1/cars", "/api/v1/search-car/cars", "/v3/api-docs/**",
                      "/swagger-ui/**",
                      "/swagger-ui.html")
               .permitAll()
              //.requestMatchers("/api/v1/cars/add-car","/api/v1/cars").hasRole("CONTENTMANAGER")
                .requestMatchers("/api/v1/cars/add-car","/api/v1/cars").permitAll()
                .anyRequest().authenticated();
        return  http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
