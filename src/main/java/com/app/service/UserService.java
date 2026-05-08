package com.app.service;

import com.app.entity.User;
import com.app.exception.InvalidCredentialsException;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserService {
    private  UserRepository userRepository;
    private JWTService jwtService;
    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public  String verifyLogin(
            LoginDto dto
    ){
        logger.debug("Verifying login for username: {}", dto.getUserName());
        Optional<User> byUserName = userRepository.findByUserName(dto.getUserName());
        if(byUserName.isPresent()){
            User user=byUserName.get();
            logger.debug("User found for username: {}", dto.getUserName());
            if (BCrypt.checkpw(dto.getPassword(),user.getPassword())) {
                logger.info("Password matched for username: {}", dto.getUserName());
                String token = jwtService.genrateToken(user.getUserName());

                logger.info("JWT token generated successfully for username: {}", dto.getUserName());

                return token;
            }
            logger.warn("Invalid password attempt for username: {}", dto.getUserName());


    }
        logger.error("Authentication failed for username: {}", dto.getUserName());
        //return null;
        throw new InvalidCredentialsException("Invalid username or password");
}
}
