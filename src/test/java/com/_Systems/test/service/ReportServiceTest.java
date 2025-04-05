package com._Systems.test.service;

import com._Systems.test.model.Dish;
import com._Systems.test.model.Meal;
import com._Systems.test.model.MealItem;
import com._Systems.test.model.User;
import com._Systems.test.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    private MealRepository mealRepository;
    private UserService userService;
    private ReportService reportService;

    private final LocalDate today = LocalDate.now();
    private final User user = User.builder()
            .id(1L)
            .name("test")
            .email("test@mail.com")
            .age(25)
            .weight(60)
            .height(165)
            .goal(User.Goal.LOSE_WEIGHT)
            .dailyCalorieTarget(1400)
            .build();

    @BeforeEach
    void setup() {
        mealRepository = mock(MealRepository.class);
        userService = mock(UserService.class);
        reportService = new ReportService(mealRepository, userService);
    }

    @Test
    void getDailyReport_shouldReturnSumOfCalories() {
        Dish dish1 = Dish.builder().calories(200).build();
        Dish dish2 = Dish.builder().calories(500).build();

        Meal meal = Meal.builder()
                .date(today)
                .user(user)
                .items(List.of(
                        MealItem.builder().dish(dish1).servings(1).build(),
                        MealItem.builder().dish(dish2).servings(2).build()
                ))
                .build();

        when(userService.getUserById(1L)).thenReturn(user);
        when(mealRepository.findAllByUserAndDate(user, today)).thenReturn(List.of(meal));

        ReportService.DailyReport report = reportService.getDailyReport(1L, today);

        assertEquals(200 + 2 * 500, report.totalCalories(), 0.1);
        assertEquals(1, report.meals().size());
    }

    @Test
    void isWithinCalorieLimit_shouldReturnTrue() {
        Dish dish = Dish.builder().calories(300).build();
        Meal meal = Meal.builder()
                .date(today)
                .user(user)
                .items(List.of(MealItem.builder().dish(dish).servings(2).build()))
                .build();

        when(userService.getUserById(1L)).thenReturn(user);
        when(mealRepository.findAllByUserAndDate(user, today)).thenReturn(List.of(meal));

        assertTrue(reportService.isWithinCalorieLimit(1L, today));
    }

    @Test
    void getCalorieHistory_shouldReturnGroupedCalories() {
        Dish d1 = Dish.builder().calories(300).build();
        Dish d2 = Dish.builder().calories(100).build();

        Meal m1 = Meal.builder()
                .date(today)
                .user(user)
                .items(List.of(MealItem.builder().dish(d1).servings(1).build()))
                .build();

        Meal m2 = Meal.builder()
                .date(today)
                .user(user)
                .items(List.of(MealItem.builder().dish(d2).servings(2).build()))
                .build();

        when(userService.getUserById(1L)).thenReturn(user);
        when(mealRepository.findAllByUser(user)).thenReturn(List.of(m1, m2));

        Map<LocalDate, Double> result = reportService.getCalorieHistory(1L);
        assertEquals(500, result.get(today), 0.1); // 300 + 2*100
    }
}