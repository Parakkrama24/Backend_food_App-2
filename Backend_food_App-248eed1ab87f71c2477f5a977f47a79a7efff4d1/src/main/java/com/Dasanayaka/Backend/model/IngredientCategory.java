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
    private Resturent restorent;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Corrected mappedBy attribute
    private List<IngreadiantItem> ingredieantItem = new ArrayList<>();
}
