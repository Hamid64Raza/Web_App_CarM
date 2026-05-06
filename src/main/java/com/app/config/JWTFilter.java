package com.app.config;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private UserRepository userRepository;

    public JWTFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain
            ) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer")){
            String substringToken = token.substring(8, token.length()-1);
            String userName = jwtService.getUserName(substringToken);
            Optional<User> opUser = userRepository.findByUserName(userName);
            if(opUser.isPresent()){
                User user = opUser.get();
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
                );
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            
            System.out.println(userName);
        }
      filterChain.doFilter(request,response);
    }
}
