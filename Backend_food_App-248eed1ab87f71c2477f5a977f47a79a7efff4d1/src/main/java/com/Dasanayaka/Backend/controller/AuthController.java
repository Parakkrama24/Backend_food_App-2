package com.Dasanayaka.Backend.controller;

import com.Dasanayaka.Backend.config.JwtProvider;
import com.Dasanayaka.Backend.model.Cart;
import com.Dasanayaka.Backend.model.USER_ROLE;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.repository.CartRepository;
import com.Dasanayaka.Backend.repository.UserRepository;
import com.Dasanayaka.Backend.request.LoginRequest;
import com.Dasanayaka.Backend.response.AuthResponse;
import com.Dasanayaka.Backend.service.CustomerUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsSevice customerUserDetailsSevice;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {
        try {
            User isEmailExit = userRepository.findByEmail(user.getUsername());
            if (isEmailExit != null) {
                throw new IllegalStateException("Email is already in use by another account");
            }

            User createUser = new User();
            createUser.setEmail(user.getUsername());
            createUser.setFullName(user.getFullName());
            createUser.setRole(user.getRole());
            createUser.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(createUser);

            Cart cart = new Cart();
            cart.setCustomer(createUser);
            cartRepository.save(cart);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generatedToken(authentication);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            authResponse.setMessage("Register success");
            authResponse.setRole(savedUser.getRole());

            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", "An error occurred during signup: " + e.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signInHandler(@RequestBody LoginRequest loginRequest) {
//        try {
//            UserDetails userDetails = customerUserDetailsSevice.loadUserByUsername(loginRequest.getEmail());
//            if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
//                throw new BadCredentialsException("Invalid username or password");
//            }
            try {
                User userDetails = userRepository.findByEmail(loginRequest.getEmail());
                if (userDetails == null) {
                    throw new IllegalStateException("Email is not use");
                }

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generatedToken(authentication);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            authResponse.setMessage("Login success");
            authResponse.setRole(USER_ROLE.valueOf(((Collection<? extends GrantedAuthority>) userDetails.getAuthorities()).stream().findFirst().orElseThrow().getAuthority()));

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", "An error occurred during signup: " + e.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
        }
    }
}
