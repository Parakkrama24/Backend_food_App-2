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

    private String name;

    @OneToMany(mappedBy = "catagory")
    private List<Resturent> resturents;

    @OneToMany(mappedBy = "foodCategory")
    private List<Food> foods;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public List<Resturent> getResturents() {
        return resturents;
    }

    public void setResturents(List<Resturent> resturents) {
        this.resturents = resturents;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}