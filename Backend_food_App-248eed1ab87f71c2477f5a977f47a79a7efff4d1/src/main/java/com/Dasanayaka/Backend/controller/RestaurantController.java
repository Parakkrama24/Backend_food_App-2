package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.dto.RestorentsDto;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.CreateRestorentRequest;
import com.Dasanayaka.Backend.service.RestorentService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private RestorentService restorentService;

    @Autowired
    private Userservice userservice;

    @GetMapping("/search")
    public ResponseEntity<List<Resturent>> searchRestaurant(

            @RequestHeader("Authorization") String jwt,
            @RequestParam  String keyword
    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
       List< Resturent > resturent = restorentService.serchRestorent(keyword);
        return  new ResponseEntity<>(resturent, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Resturent>> getAllRestaurant(

            @RequestHeader("Authorization")  String jwt

    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
       List< Resturent > restaurant = restorentService.getAllResthorents();
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resturent> findRestaurantByUserId   (

            @RequestHeader("Authorization") String jwt,
            @PathVariable long id

    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Resturent  resturent = restorentService.findRestorentById(id);
        return  new ResponseEntity<>(resturent, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourite")
    public ResponseEntity<RestorentsDto> addToFavourite   (

            @RequestHeader("Authorization") String jwt,
            @PathVariable long id

    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        RestorentsDto  resturent = restorentService.addToFavorites(id,user);


        return  new ResponseEntity<>(resturent, HttpStatus.OK);
    }
}
