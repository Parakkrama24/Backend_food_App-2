package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.model.CartItem;
import com.Dasanayaka.Backend.model.Oder;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.AddCartItemRequest;
import com.Dasanayaka.Backend.request.OderRequest;
import com.Dasanayaka.Backend.service.OderService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OderController {

    @Autowired
    private OderService oderService;

    @Autowired
    private Userservice userservice;

    @PostMapping("/order")
    private ResponseEntity<Oder> createOder(@RequestBody OderRequest req, @RequestHeader("Authorization ") String jwt) throws Exception {

        User user =userservice.findUsserByJwtToken(jwt);
        Oder oder   = oderService.createOder(req,user);
        return new ResponseEntity<>(oder, HttpStatus.CREATED);

        }

        @GetMapping("/order/user")
    private ResponseEntity<List<Oder>> getOderHistory(@RequestBody OderRequest req, @RequestHeader("Authorization ") String jwt) throws Exception {

        User user =userservice.findUsserByJwtToken(jwt);
        List<Oder> oders  = oderService.getUsersOder(user.getId());
        return new ResponseEntity<>(oders, HttpStatus.OK);

        }

}
