package com.Dasanayaka.Backend.model;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Resturent restaurant;

    @Enumerated(EnumType.STRING)
    private Category foodCategory;

    private boolean isVegetarian;

    private boolean isSeasonal;

    private Date createdDate;

    // Custom setters for foodCategory and restaurant
    public void setFoodCategory(Category foodCategory) {
        this.foodCategory = foodCategory;
    }

    public void setRestaurant(Resturent restaurant) {
        this.restaurant = restaurant;
    }

    // Custom setter for description with type checking
    public void setDescription(Object description) {
        if (description instanceof String) {
            this.description = (String) description;
        } else {
            throw new IllegalArgumentException("Description should be a string.");
        }
    }

    // Custom setter for name with type checking
    public void setName(Object name) {
        if (name instanceof String) {
            this.name = (String) name;
        } else {
            throw new IllegalArgumentException("Name should be a string.");
        }
    }

    // Custom setter for images with type checking
    public void setImages(Object images) {
        if (images instanceof List<?>) {
            for (Object image : (List<?>) images) {
                if (!(image instanceof String)) {
                    throw new IllegalArgumentException("All elements of the image list should be strings.");
                }
            }
            this.images = (List<String>) images;
        } else {
            throw new IllegalArgumentException("Images should be a list of strings.");
        }
    }

    // Custom setter for price with type checking
    public void setPrice(Object price) {
        if (price instanceof Long) {
            this.price = (Long) price;
        } else if (price instanceof Number) {
            this.price = ((Number) price).longValue(); // Allow conversion from other Number types
        } else {
            throw new IllegalArgumentException("Price should be a Long or a Number that can be converted to Long.");
        }
    }
}
