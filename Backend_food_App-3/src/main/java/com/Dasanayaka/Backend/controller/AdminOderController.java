package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.model.Oder;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.service.OderService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOderController {

    @Autowired
    private OderService oderService;

    @Autowired
    private Userservice userservice;

    @GetMapping("/order/restaurant/{id}")
    private ResponseEntity<List<Oder>> getOderHistory(@PathVariable Long id,
            @RequestParam (required = false) String oderStatus, @RequestHeader("Authorization ") String jwt) throws Exception {

        User user =userservice.findUsserByJwtToken(jwt);
        List<Oder> oders  = oderService.getRestaurantOrders(id,oderStatus);
        return new ResponseEntity<>(oders, HttpStatus.OK);

    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    private ResponseEntity<Oder> updateOderStatus(@PathVariable Long id,
            @PathVariable String  orderStatus,
         @RequestHeader("Authorization ") String jwt) throws Exception {

        User user =userservice.findUsserByJwtToken(jwt);
        Oder oders  = oderService.updateOder(id,orderStatus);
        return new ResponseEntity<>(oders, HttpStatus.OK);

    }
}
