package com.reanima.business.repository.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Details about Recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    @ApiModelProperty(notes = "Unique ID of the Recipe")
    private int recipeId;
    
    @Column(name = "recipe_name")
    @ApiModelProperty(notes = "Recipe name")
    private String recipeName;

    @Column(name = "recipe_desc")
    @ApiModelProperty(notes = "Recipe description")
    private String recipeDescription;

    @Column(name = "recipe_prep")
    @ApiModelProperty(notes = "Recipe preparation")
    private String recipePreparation;

    @Column(name = "cuisine_type")
    @ApiModelProperty(notes = "Cuisine type of the Recipe")
    private String cuisineType;

    @Column(name = "dish_type")
    @ApiModelProperty(notes = "Dish type of the Recipe")
    private String dishType;

    @CreationTimestamp
    @Column(name = "recipe_created")
    @ApiModelProperty(notes = "Recipe timestamp of creation")
    private LocalDateTime recipeCreated;

}
