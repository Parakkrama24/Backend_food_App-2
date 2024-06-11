package com.Dasanayaka.Backend.request;

import com.Dasanayaka.Backend.model.Address;
import com.Dasanayaka.Backend.model.Contactinformation;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestorentRequest {

    @jakarta.persistence.Id
    private Long Id;

    private String name;

    private String description;

    private String cuisineType;

    private Address address;

  private String contactinformation;

    private  String openHours;

    private List<String> images;


    public Address getAddress() {

        return  address;
    }

    public String  getContactInformation() {
        return  contactinformation;
    }

    public Object getImages() {
        return  images;
    }

    public Object getCuisineType() {
        return  cuisineType;
    }

    public Object getDiscription() {
        return  description;
    }

    public Object getName() {
        return  name;
    }

    public Object getOpeningHours() {
        return  openHours;
    }
}
