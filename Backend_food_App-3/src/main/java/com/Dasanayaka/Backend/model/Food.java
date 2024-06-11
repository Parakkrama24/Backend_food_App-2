package com.Dasanayaka.Backend.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Catagory foodCategory;

    private boolean isVegetarian;

    private boolean isSeasonal;

    @ManyToOne
    @JoinColumn(name = "ingredient_item_id")
    private IngreadiantItem ingredientItem;

    private Date createdDate;

    // Custom setters for foodCategory and restaurant
    public void setFoodCategory(Catagory foodCategory) {
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

    public void setRestauraent(Resturent resturent) {
        this.restaurant = restaurant;
    }

//    public void setIngredientItem(Object ingredientItem) {
//        if (ingredientItem instanceof IngreadiantItem) {
//            this.ingreadiantItem = (IngreadiantItem) ingredientItem;
//        } else {
//            throw new IllegalArgumentException("IngredientItem should be an instance of IngredientItem.");
//        }
//    }

    public void setVegetarian(Object vegetarian) {
        if (vegetarian instanceof Boolean) {
            this.isVegetarian = (Boolean) vegetarian;
        } else {
            throw new IllegalArgumentException("Vegetarian should be a boolean.");
        }
    }

    public void setSeasonal(Object seasonal) {
        if (seasonal instanceof Boolean) {
            this.isSeasonal = (Boolean) seasonal;
        } else {
            throw new IllegalArgumentException("Seasonal should be a boolean.");
        }
    }
}
