package com.Dasanayaka.Backend.request;


import lombok.Data;

@Data
public class updateCartItemRequest {

    private Long cartItemId;
    private  int quantity;

    public int getQuantity() {
        return quantity;
    }
    public  Long getCartItemId(){
        return  cartItemId;
    }
}
