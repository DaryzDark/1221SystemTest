package com._Systems.test.controller;

import com._Systems.test.model.Dish;
import com._Systems.test.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    public Dish createDish(@Valid @RequestBody Dish dish) {
        return dishService.createDish(dish);
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public Dish getDish(@PathVariable Long id) {
        return dishService.getDishById(id);
    }
}
