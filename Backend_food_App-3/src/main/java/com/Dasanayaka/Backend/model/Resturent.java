package com.Dasanayaka.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resturents")
public class Resturent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User owner;

    private String name;

    private String description;

    private String cuisineType;

    @OneToOne
    private Address address;

    private String openingHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Oder> order = new ArrayList<>(); // Ensure property name matches the target entity

    @ElementCollection
    @Column(length = 1000)
    private List<String> images = new ArrayList<>();

    private LocalDateTime registrationDate;

    private boolean isOpen;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();


    private String contactInformation; // Add this field to store contact information

    @ManyToOne
    @JoinColumn(name = "catagory_id")
    private Catagory catagory;



    public void setAddress(Address _address) {
        this.address = _address;
    }

    public void setContactImformation(Object contactInformation) {
        if (contactInformation instanceof String) {
            this.contactInformation = (String) contactInformation;
        } else {
            throw new IllegalArgumentException("Contact information should be a string.");
        }
    }

    public void setImages(Object _images) {
        if (_images instanceof List<?>) {
            for (Object image : (List<?>) _images) {
                if (!(image instanceof String)) {
                    throw new IllegalArgumentException("All elements of the image list should be strings.");
                }
            }
            this.images = (List<String>) _images;
        } else {
            throw new IllegalArgumentException("Images should be a list of strings.");
        }
    }

    public void setCuisineType(Object _cuisineType) {
        if (_cuisineType instanceof String) {
            this.cuisineType = (String) _cuisineType;
        } else {
            throw new IllegalArgumentException("Cuisine type should be a string.");
        }
    }

    public void setDescription(Object _description) {
        if (_description instanceof String) {
            this.description = (String) _description;
        } else {
            throw new IllegalArgumentException("Description should be a string.");
        }
    }

    public void setName(Object _name) {
        if (_name instanceof String) {
            this.name = (String) _name;
        } else {
            throw new IllegalArgumentException("Name should be a string.");
        }
    }

    public void setOpeningHours(Object _openingHours) {
        if (_openingHours instanceof String) {
            this.openingHours = (String) _openingHours;
        } else {
            throw new IllegalArgumentException("Opening hours should be a string.");
        }
    }

    public void setRegistrationDate(LocalDateTime _registrationDate) {
        this.registrationDate = _registrationDate;
    }

    public void setOwner(User _owner) {
        this.owner = _owner;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public Object getImage() {

        return  images;
    }

    public boolean isOpen() {
        return  isOpen;
    }

    public void setOpen(boolean b) {
        isOpen= b;
    }

    public Long getId(){
        return id;
    }

    public  void setId(Long _id){
        id= _id;
    }

    public <E> List<E> getFood() {
        List<E> foodList = new ArrayList<>();
        return foodList;
    }


    public <E> List<E> getOrders() {
        List<E>  order= new ArrayList<>();
        return order;
    }
}
