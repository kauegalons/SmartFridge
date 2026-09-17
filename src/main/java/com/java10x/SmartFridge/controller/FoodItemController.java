package com.java10x.SmartFridge.controller;

import com.java10x.SmartFridge.model.FoodItem;
import com.java10x.SmartFridge.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem saved = foodItemService.save(foodItem);
        return ResponseEntity.ok(saved);
    }

    //GET

    //UPDATE

    //DELETE
}
