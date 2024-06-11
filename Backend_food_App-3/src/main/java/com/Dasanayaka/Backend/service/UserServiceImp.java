package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.config.JwtProvider;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp  implements Userservice {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUsserByJwtToken(String jwt) throws Exception {
         String email= jwtProvider.getEmailFrom_JwtToken(jwt);
         User user = findUserByEmai(email);
        System.out.println("User fond");
         return  user;

    }

    @Override
    public User findUserByEmai(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user== null){
            throw  new Exception("User not fond ");
        }

        return user;
    }
}
