package com._Systems.test.controller;

import com._Systems.test.model.Dish;
import com._Systems.test.model.Meal;
import com._Systems.test.model.MealItem;
import com._Systems.test.model.User;
import com._Systems.test.service.DishService;
import com._Systems.test.service.MealService;
import com._Systems.test.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final UserService userService;
    private final DishService dishService;

    @PostMapping
    public Meal createMeal(@Valid @RequestBody CreateMealRequest request) {
        User user = userService.getUserById(request.userId());

        Meal meal = Meal.builder()
                .user(user)
                .date(request.date())
                .build();

        List<MealItem> items = request.items().stream().map(entry -> {
            Dish dish = dishService.getDishById(entry.dishId());
            return MealItem.builder()
                    .meal(meal)
                    .dish(dish)
                    .servings(entry.servings())
                    .build();
        }).collect(Collectors.toList());

        meal.setItems(items);
        return mealService.saveMeal(meal);
    }

    public record CreateMealRequest(
            @NotNull Long userId,
            @NotNull LocalDate date,
            @NotEmpty List<MealItemEntry> items
    ) {}

    public record MealItemEntry(
            @NotNull Long dishId,
            @Min(1) int servings
    ) {}
}
