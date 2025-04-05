package com._Systems.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "meal_items")
public class MealItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int servings;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    @JsonIgnore
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
}

