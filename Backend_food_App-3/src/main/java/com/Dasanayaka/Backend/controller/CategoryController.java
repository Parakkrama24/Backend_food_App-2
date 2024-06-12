package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.model.Catagory;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.service.CategoryService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Userservice userservice;

    @PostMapping("/admin/category")
    public ResponseEntity<Catagory> createCategory(@RequestBody Catagory catagory,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user   =userservice.findUsserByJwtToken(jwt);
        Catagory createdCategory= categoryService.CreateCategory(catagory.getName(),user.getId());
        return  new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Catagory>> getRestaurantCategory(
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user   =userservice.findUsserByJwtToken(jwt);
         List<Catagory> Categories= categoryService.findCategoryByRestaurantId(user.getId());
        return  new ResponseEntity<>(Categories, HttpStatus.CREATED);

    }
}
