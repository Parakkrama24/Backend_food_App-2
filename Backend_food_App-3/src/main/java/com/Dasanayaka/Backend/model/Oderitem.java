package com.Dasanayaka.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oderitem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
   private Food food;

    private  int quantity;

    private Long totalPrice;

    private List<String> ingredieants;

    public void setFood(Food _food) {
        food= _food;
    }


    public void setIngrediants(List<String> _ingredients) {
        ingredieants=_ingredients;
    }

    public void setQuantity(int _quantity) {
        quantity=_quantity;
    }

    public void setTotolPrice(Long _price) {
        totalPrice=_price;
    }
}
