package com.Dasanayaka.Backend.response;

import lombok.Data;

@Data
public class MessageResponse {

    private  String message;

    public void setMassage(String _message) {
        message= _message;
    }
}
