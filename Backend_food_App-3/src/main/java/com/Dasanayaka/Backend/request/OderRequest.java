package com.Dasanayaka.Backend.request;

import com.Dasanayaka.Backend.model.Address;
import lombok.Data;

@Data
public class OderRequest {
    private Long restaurantId;

    private Address delivaryAddress;
}
