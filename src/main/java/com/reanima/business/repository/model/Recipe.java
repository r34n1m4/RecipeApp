package com.reanima.business.repository.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private int recipeId;
    
    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "recipe_desc")
    private String recipeDescription;

    @Column(name = "recipe_prep")
    private String recipePreparation;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "dish_type")
    private String dishType;

    @CreationTimestamp
    @Column(name = "recipe_created")
    private LocalDateTime recipeCreated;

}
