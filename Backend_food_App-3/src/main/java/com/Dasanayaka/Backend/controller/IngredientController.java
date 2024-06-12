package com.Dasanayaka.Backend.controller;

import com.Dasanayaka.Backend.model.IngreadiantItem;
import com.Dasanayaka.Backend.model.IngredientCategory;
import com.Dasanayaka.Backend.request.IngredientCategoryRequest;
import com.Dasanayaka.Backend.request.IngredientRequest;
import com.Dasanayaka.Backend.service.IngrediantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngrediantService ingrediantService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {
        IngredientCategory item =ingrediantService.createIngredientCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngreadiantItem> createIngredientItem(
            @RequestBody IngredientRequest req
            ) throws Exception {
        IngreadiantItem item =ingrediantService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }@PutMapping("/{id}/stoke")
    public ResponseEntity<IngreadiantItem> updateIngrediantStok(
            @PathVariable Long id
            ) throws Exception {
        IngreadiantItem item =ingrediantService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngreadiantItem> >getRestaurantIngredient(
            @PathVariable Long id
            ) throws Exception {
        List<IngreadiantItem> items =ingrediantService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory> >getRestaurantIngredientCategory(
            @PathVariable Long id
            ) throws Exception {
        List<IngredientCategory> items =ingrediantService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
