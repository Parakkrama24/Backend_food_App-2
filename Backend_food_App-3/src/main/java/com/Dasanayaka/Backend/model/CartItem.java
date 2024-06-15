package com.Dasanayaka.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Food food;

    private int quantity;

    private List<String> ingrediants;

    private Long totolPrice;

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getId() {
        return id;
    }

    public void setTotalPrice(long l) {
        totolPrice = l;
    }

    public void setFood(Food _food) {
        food = _food;
    }

    public void setCart(Cart _cart) {
        cart = _cart;
    }

    public void setQuantity(int _quantity) {
        quantity = _quantity;
    }

    public List<String> getIngredients() {
        return ingrediants;
    }

    public void setIngredients(List<String> _ingredients) {
        ingrediants = _ingredients;
    }

    public Long getPrice() {
        return totolPrice;
    }
}
