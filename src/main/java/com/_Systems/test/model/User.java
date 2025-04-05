package com._Systems.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Min(10) @Max(200)
    private int age;

    @DecimalMin("30.0") @DecimalMax("300.0")
    private double weight;

    @Min(100) @Max(250)
    private int height;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column(name = "daily_calorie_target")
    private double dailyCalorieTarget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Meal> meals;

    public enum Goal {
        LOSE_WEIGHT, MAINTAIN_WEIGHT, GAIN_WEIGHT
    }

}
