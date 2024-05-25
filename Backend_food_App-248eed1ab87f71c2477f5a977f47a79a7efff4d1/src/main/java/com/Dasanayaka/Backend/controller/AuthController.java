package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.config.JwtProvider;
import com.Dasanayaka.Backend.model.Cart;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.repository.CartRepository;
import com.Dasanayaka.Backend.repository.UserRepository;
import com.Dasanayaka.Backend.response.AuthResponse;
import com.Dasanayaka.Backend.service.CustomerUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user ) throws Exception {
        User isEmailExit= userRepository.findByEmail(user.getUsername());
        if(isEmailExit!=null){
            throw  new Exception("EMail is already using another acount ");
        }

        User craerteUser = new User();
        craerteUser.setEmail(user.getUsername());
        craerteUser.setFullName(user.getFullName());
        craerteUser.setRole(user.getRole());
        craerteUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser= userRepository.save(craerteUser);

        Cart cart=  new Cart();
        cart.setCustomer(craerteUser);
        cartRepository.save(cart);

        Authentication authentication= new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= jwtProvider.generatedToken(authentication);
        AuthResponse authResponse =new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setMessage("Register success");
        authResponse.setRole(savedUser.getRole());
        return  new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

  //  public ResponseEntity<AuthResponse> signIn (@RequestBody String)
}
