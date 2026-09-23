package com.java10x.SmartFridge.mapper;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {

    //from dto to model
    public FoodItem map(FoodDTO foodDTO) {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodDTO.getName());
        foodItem.setCategory(foodDTO.getCategory());
        foodItem.setQuantity(foodDTO.getQuantity());
        foodItem.setExpirationDate(foodDTO.getExpirationDate());

        return foodItem;
    }

    public FoodDTO map(FoodItem foodItem){
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(foodItem.getId());
        foodDTO.setName(foodItem.getName());
        foodDTO.setCategory(foodItem.getCategory());
        foodDTO.setQuantity(foodItem.getQuantity());
        foodDTO.setExpirationDate(foodItem.getExpirationDate());

        return foodDTO;
    }

}
