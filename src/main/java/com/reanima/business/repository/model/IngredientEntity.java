package com.reanima.business.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingr_id")
    private int ingredientId;

    @Column(name = "ingr_name")
    @ApiModelProperty(notes = "Ingredient name")
    private String ingredientName;

    @Column(name = "ingr_desc")
    @ApiModelProperty(notes = "Ingredient description")
    private String ingredientDescription;

    @Column(name = "ingr_type")
    @ApiModelProperty(notes = "Ingredient type")
    private String ingredientType;

    @Column(name = "calories")
    @ApiModelProperty(notes = "Ingredient calories value")
    private float calories;

    @Column(name = "carbohydrates")
    @ApiModelProperty(notes = "Ingredient carbohydrates value")
    private float carbohydrates;

    @Column(name = "fat")
    @ApiModelProperty(notes = "Ingredient fat value")
    private float fat;

    @Column(name = "protein")
    @ApiModelProperty(notes = "Ingredient protein value")
    private float protein;

    @Column(name = "water")
    @ApiModelProperty(notes = "Ingredient water value")
    private float water;

    @Column(name = "cholesterol")
    @ApiModelProperty(notes = "Ingredient cholesterol value")
    private float cholesterol;

    @CreationTimestamp
    @Column(name = "ingr_created")
    @ApiModelProperty(notes = "Ingredient timestamp of creation")
    private LocalDateTime ingredientCreated;

    @OneToMany(mappedBy = "ingredientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<RecipesIngredientsEntity> recipesIngredients = new HashSet<>();
}
