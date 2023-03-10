package com.reanima.business.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "recipes_ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecipesIngredientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipes_ingredients_id")
    private int recipesIngredientsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonBackReference
    private RecipeEntity recipeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    @JsonBackReference
    private IngredientEntity ingredientEntity;

    @Column(name = "quantity")
    private float quantity;

    public RecipesIngredientsEntity(RecipeEntity recipeEntity, IngredientEntity ingredientEntity, float quantity) {
        this.recipeEntity = recipeEntity;
        this.ingredientEntity = ingredientEntity;
        this.quantity = quantity;
    }

}
