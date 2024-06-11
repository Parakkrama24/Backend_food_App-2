package com.Dasanayaka.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingreadiant_items") // Use a more descriptive table name
public class IngreadiantItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id") // Add JoinColumn to specify the foreign key column
    private IngredientCategory category; // Ensure this matches the mappedBy attribute in IngredientCategory

    @JsonIgnore
    @ManyToOne
    private Resturent restorent;


    private boolean inStock = true;
}
