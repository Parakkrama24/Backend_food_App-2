package com.Dasanayaka.Backend.request;

import com.Dasanayaka.Backend.model.Address;
import lombok.Data;

@Data
public class OderRequest {
    private Long restaurantId;

    private Address delivaryAddress;

    public Address getDilevaryAddress() {
        return delivaryAddress;
    }

    public Long getResaurantId() {
        return restaurantId;
    }
}
