package com.Dasanayaka.Backend.controller;

import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.CreateRestorentRequest;
import com.Dasanayaka.Backend.response.MessageResponse;
import com.Dasanayaka.Backend.service.RestorentService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping("/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    private RestorentService restorentService;

    @Autowired
    private Userservice userservice;

    @GetMapping
    public ResponseEntity<String> HomeController(){
        return  new ResponseEntity<>("welcome to food ", HttpStatus.OK);
    }

    @PostMapping()
    public  ResponseEntity<Resturent> createRestaurent(
            @RequestBody CreateRestorentRequest req,
            @RequestHeader ("Authorization") String jwt

            ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Resturent resturent = restorentService.createRestorent(req,user);
        return  new ResponseEntity<>(resturent,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Resturent> updateRestaurent(
            @RequestBody CreateRestorentRequest req,
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Resturent resturent = restorentService.updateRestorent(id,req);
        return  new ResponseEntity<>(resturent,HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<MessageResponse> deleteRestaurent(

            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        restorentService.deleteRestorent(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMassage("Restaurant deteded sucssfully ");
        return  new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

    @GetMapping("/user")
    public  ResponseEntity<Resturent> findRestaurantByuserId(

            @RequestHeader ("Authorization") String jwt

    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);

        Resturent resturent=  restorentService.getRestorentByUserId(user.getId());

        return  new ResponseEntity<>(resturent,HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public  ResponseEntity<Resturent> updateRestaurantStatus(

            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id

    ) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);

        Resturent resturent=  restorentService.updateRestorentStatus(id);

        return  new ResponseEntity<>(resturent,HttpStatus.OK);
    }
}
