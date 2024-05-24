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
}
