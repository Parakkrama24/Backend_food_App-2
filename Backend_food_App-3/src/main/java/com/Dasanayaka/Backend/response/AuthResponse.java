package com.Dasanayaka.Backend.response;

import com.Dasanayaka.Backend.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String Jwt ;
    private String  message;
    private USER_ROLE role;

    public  void setJwt(String jwt) {

        this.Jwt= jwt;
    }

    public void setMessage(String message) {
        this.message= message;
    }

    public  void setRole(USER_ROLE role){
        this.role= role;
    }
}
