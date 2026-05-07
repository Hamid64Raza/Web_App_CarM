package com.app.controller;

import com.app.entity.User;
import com.app.exception.InvalidCredentialsException;
import com.app.exception.ResourceAlreadyExistsException;
import com.app.payload.JWTTokenDto;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import com.app.service.OTPService;
import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private OTPService otpService;
    private JWTService jwtService;

    public AuthController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, OTPService otpService, JWTService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
            ){
        Optional<User> byUserName = userRepository.findByUserName(user.getUserName());
        if(byUserName.isPresent()){
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        Optional<User> byEmailId = userRepository.findByEmailId(user.getEmailId());
        if(byEmailId.isPresent()){
          //  return new ResponseEntity<>("Email exits",HttpStatus.INTERNAL_SERVER_ERROR);
            throw  new ResourceAlreadyExistsException("Email already exists");
        }
   //     String encodedPassword = passwordEncoder.encode(user.getPassword());
     //   user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return new ResponseEntity<>("User Added",HttpStatus.CREATED);
    }

    @PostMapping("/content-manger-signup")
    public ResponseEntity<?> createContentManagerAccount(
            @RequestBody User user
    ){
        Optional<User> byUserName = userRepository.findByUserName(user.getUserName());
        if(byUserName.isPresent()){
            //return new ResponseEntity<>("Username exits", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        Optional<User> byEmailId = userRepository.findByEmailId(user.getEmailId());
        if(byEmailId.isPresent()){
           // return new ResponseEntity<>("Email exits",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        //     String encodedPassword = passwordEncoder.encode(user.getPassword());
        //   user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("User Added",HttpStatus.CREATED);
    }

    @PostMapping("/blog-manger-signup")
    public ResponseEntity<?> createBlogManagerAccount(
            @RequestBody User user
    ){
        Optional<User> byUserName = userRepository.findByUserName(user.getUserName());
        if(byUserName.isPresent()){
          //return new ResponseEntity<>("Username exits", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        Optional<User> byEmailId = userRepository.findByEmailId(user.getEmailId());
        if(byEmailId.isPresent()){
           // return new ResponseEntity<>("Email exits",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        //     String encodedPassword = passwordEncoder.encode(user.getPassword());
        //   user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOGMANABER");
        userRepository.save(user);
        return new ResponseEntity<>("User Added",HttpStatus.CREATED);
    }
    @GetMapping("/getMessage")
    public  String getMessage(

    ){
        return "hello";

    }

    @PostMapping("/userLogin")
    public ResponseEntity<?> userLogIn(
          @RequestBody  LoginDto dto
    ){
        String jwtToken = userService.verifyLogin(dto);
        if(jwtToken!=null){
            JWTTokenDto jwtTokenDto=new JWTTokenDto();
            jwtTokenDto.setToken(jwtToken);
            jwtTokenDto.setTokenType("JWT");
            return new ResponseEntity<>(jwtTokenDto,HttpStatus.CREATED);
        }
        else //return new ResponseEntity<>("Invalid Token", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new InvalidCredentialsException("Invalid username or password");
    }
    @PostMapping("/messages")
    public String getMessages(){
        return  "Hello";
    }

    @PostMapping("/login-otp")
    public String generateOtp(
            @RequestParam String mobile
    ){
        Optional<User> opUser = userRepository.findByMobile(mobile);
        if(opUser.isPresent()) {
            String otp = otpService.generateOTP(mobile);
            return otp + " " + mobile;
        }
        return "User not found";
    }

    @PostMapping("/validate-otp")
    public  String validateOtp(
           @RequestParam String mobile,
           @RequestParam String otp
    ){
        boolean status= otpService.validateOTP(mobile,otp);
        if(status){
            //generate JWT Token
            Optional<User> opUser = userRepository.findByMobile(mobile);
            if(opUser.isPresent()){
                String jwtToken = jwtService.genrateToken(opUser.get().getUserName());
                return jwtToken;
            }
        }
        return status? "OTP validated successfully": "Invalid OTP";
    }
}
