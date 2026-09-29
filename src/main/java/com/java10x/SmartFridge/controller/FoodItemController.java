package com.java10x.SmartFridge.controller;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO food) {
        FoodDTO saved = foodItemService.createFood(food);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getAllFood(){
        List<FoodDTO> foodList = foodItemService.listAllFood();
        if(foodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(foodList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById (@PathVariable Long id){
        FoodDTO food = foodItemService.findById(id);
        if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food with id " + id + " not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        if(!foodItemService.deleteFood(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food with id " + id + " not found.");
        }
        return ResponseEntity.ok("Food item deleted.");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @RequestBody FoodDTO food) {
        FoodDTO updatedFood = foodItemService.updateFood(id, food);
        if (updatedFood == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food with id " + id + " not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedFood);
    }
}
