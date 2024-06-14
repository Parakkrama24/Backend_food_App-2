package com.Dasanayaka.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient_categories") // Use a more descriptive table name
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Resturent restaurant;

    @OneToMany(mappedBy = "category")
    private List<IngreadiantItem> ingredientItems;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Corrected mappedBy attribute
    private List<IngreadiantItem> ingredieantItem = new ArrayList<>();

    public void setRestaurant(Resturent _resturent) {
        restaurant=_resturent;
    }

    public void setName(String _name) {
        name= _name;
    }

    public <E> List<E> getIngredients() {
        List<E> ingredieantList = new ArrayList<>();
        return ingredieantList;
    }
}
