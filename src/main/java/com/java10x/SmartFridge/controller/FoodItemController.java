package com.java10x.SmartFridge.controller;

import com.java10x.SmartFridge.model.FoodItem;
import com.java10x.SmartFridge.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem saved = foodItemService.create(foodItem);
        return ResponseEntity.ok(saved);
    }

    //GET BY ID

    //UPDATE

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable Long id) {
        FoodItem foodItem = foodItemService.findById(id).orElse(null);
        if(foodItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item not found");
        }else {
            foodItemService.delete(foodItem);
            return ResponseEntity.ok("Food item " + foodItem.getName() + " deleted successfully");
        }
    }
}
