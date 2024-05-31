package com.Dasanayaka.Backend.controller;

import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.CreateRestorentRequest;
import com.Dasanayaka.Backend.service.RestorentService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestorentService restorentService;

    @Autowired
    private Userservice userservice;

    @PostMapping()
    public  ResponseEntity<Resturent> createRestaurent(
            @RequestBody CreateRestorentRequest req,
            @RequestHeader ("Authorization") String jwt
            ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Resturent resturent = restorentService.createRestorent(req,user);
        return  new ResponseEntity<>(resturent,HttpStatus.CREATED);
    }

    @PutMapping()
    public  ResponseEntity<Resturent> updateRestaurent(
            @RequestBody CreateRestorentRequest req,
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Resturent resturent = restorentService.createRestorent(req,user);
        return  new ResponseEntity<>(resturent,HttpStatus.CREATED);
    }
}
