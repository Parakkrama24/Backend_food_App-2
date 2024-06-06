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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/restaurants")
public class test {

    private static final Logger logger = LoggerFactory.getLogger(AdminRestaurantController.class);

    @Autowired
    private RestorentService restaurantService;

    @Autowired
    private Userservice userService;

    @GetMapping
    public ResponseEntity<String> homeController() {
        return new ResponseEntity<>("Welcome to Food Kade", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resturent> createRestaurant(
            @RequestBody CreateRestorentRequest req,
            @RequestHeader("Authorization") String jwt) {
        try {
            User user = userService.findUsserByJwtToken(jwt);
            Resturent restaurant = restaurantService.createRestorent(req, user);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating restaurant", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Restaurant> updateRestaurant(
//            @RequestBody CreateRestaurantRequest req,
//            @RequestHeader("Authorization") String jwt,
//            @PathVariable Long id) {
//        try {
//            User user = userService.findUserByJwtToken(jwt);
//            Restaurant restaurant = restaurantService.updateRestaurant(id, req);
//            return new ResponseEntity<>(restaurant, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("Error updating restaurant", e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<MessageResponse> deleteRestaurant(
//            @RequestHeader("Authorization") String jwt,
//            @PathVariable Long id) {
//        try {
//            User user = userService.findUserByJwtToken(jwt);
//            restaurantService.deleteRestaurant(id);
//            MessageResponse messageResponse = new MessageResponse();
//            messageResponse.setMessage("Restaurant deleted successfully");
//            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("Error deleting restaurant", e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<Restaurant> findRestaurantByUserId(
//            @RequestHeader("Authorization") String jwt) {
//        try {
//            User user = userService.findUserByJwtToken(jwt);
//            Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
//            return new ResponseEntity<>(restaurant, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("Error finding restaurant by user ID", e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}/status")
//    public ResponseEntity<Restaurant> updateRestaurantStatus(
//            @RequestHeader("Authorization") String jwt,
//            @PathVariable Long id) {
//        try {
//            User user = userService.findUserByJwtToken(jwt);
//            Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
//            return new ResponseEntity<>(restaurant, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("Error updating restaurant status", e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
 //   }
}
