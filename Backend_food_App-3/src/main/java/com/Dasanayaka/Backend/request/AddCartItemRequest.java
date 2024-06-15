package com.Dasanayaka.Backend.request;


import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {
    private Long foodId;
    private int quantity;
    private List<String> Ingredients;

    public Long getFoodId() {
        return  foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }
}
