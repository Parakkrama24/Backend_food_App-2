package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.model.Food;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.CreateFoodRequest;
import com.Dasanayaka.Backend.response.MessageResponse;
import com.Dasanayaka.Backend.service.FoodService;
import com.Dasanayaka.Backend.service.RestorentService;
import com.Dasanayaka.Backend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private Userservice userservice;

    @Autowired
    private RestorentService restorentService;

    @PostMapping
    public ResponseEntity<Food>  createFood(@RequestBody CreateFoodRequest req,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user   =userservice.findUsserByJwtToken(jwt);
        Resturent resturent =restorentService.findRestorentById(req.getRestaurantId());
        Food food =foodService.createFood(req,req.getCategory(),resturent);
return   new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>  DeleteFood(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user   =userservice.findUsserByJwtToken(jwt);

        foodService.deleteFood(id);
        MessageResponse res= new MessageResponse();
        res.setMassage("Food deleted successfully");
        return   new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Food>  updateFoodAvailabilityStatus(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user   =userservice.findUsserByJwtToken(jwt);

       Food food=  foodService.updateAvailabilityStatus(id);

        return   new ResponseEntity<>(food, HttpStatus.OK);
    }
}
