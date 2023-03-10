package com.reanima.business.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<RecipesIngredientsEntity> recipeIngredients = new HashSet<>();

//    public void addIngredient(IngredientEntity ingredientEntity, float quantity) {
//        RecipesIngredientsEntity recipesIngredientsEntity = new RecipesIngredientsEntity();
//        recipesIngredientsEntity.setRecipeEntity(this);
//        recipesIngredientsEntity.setIngredientEntity(ingredientEntity);
//        recipesIngredientsEntity.setQuantity(quantity);
//        recipeIngredients.add(recipesIngredientsEntity);
//        ingredientEntity.getRecipesIngredients().add(recipesIngredientsEntity);
//    }
//
//    public void removeIngredient(IngredientEntity ingredientEntity) {
//        for (Iterator<RecipesIngredientsEntity> iterator = recipeIngredients.iterator();
//             iterator.hasNext(); ) {
//            RecipesIngredientsEntity recipesIngredientsEntity = iterator.next();
//
//            if (recipesIngredientsEntity.getRecipeEntity().equals(this) &&
//                    recipesIngredientsEntity.getIngredientEntity().equals(ingredientEntity)) {
//                iterator.remove();
//                recipesIngredientsEntity.getIngredientEntity().getRecipesIngredients().remove(recipesIngredientsEntity);
//                recipesIngredientsEntity.setRecipeEntity(null);
//                recipesIngredientsEntity.setIngredientEntity(null);
//            }
//        }
//    }
}
