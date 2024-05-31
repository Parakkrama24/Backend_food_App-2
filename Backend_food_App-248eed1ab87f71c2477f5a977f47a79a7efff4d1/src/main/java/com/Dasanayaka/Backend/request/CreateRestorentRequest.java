package com.Dasanayaka.Backend.request;

import com.Dasanayaka.Backend.model.Address;
import com.Dasanayaka.Backend.model.Contactinformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestorentRequest {

    private Long Id;

    private String name;

    private String decription;

    private String cuisineType;

    private Address address;

    private Contactinformation contactinformation;

    private  String openHours;

    private List<String> images;


    public Address getAddress() {

        return  address;
    }

    public Object getContactInformation() {
        return  contactinformation;
    }

    public Object getImages() {
        return  images;
    }

    public Object getCuisineType() {
        return  cuisineType;
    }

    public Object getDiscription() {
        return  decription;
    }

    public Object getName() {
        return  name;
    }

    public Object getOpeningHours() {
        return  openHours;
    }
}
