package com.Dasanayaka.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // Add a field to avoid an empty entity

    @OneToMany(mappedBy = "foodCategory")
    private List<Food> foods;

    public String getName() {
        return name;
    }
}