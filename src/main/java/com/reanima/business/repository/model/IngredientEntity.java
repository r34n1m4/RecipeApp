package com.reanima.business.repository.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String ingredientName;

    @Column(name = "ingr_desc")
    private String ingredientDescription;

    @Column(name = "ingr_type")
    private String ingredientType;

    @Column(name = "calories")
    private float calories;

    @Column(name = "carbohydrates")
    private float carbohydrates;

    @Column(name = "fat")
    private float fat;

    @Column(name = "protein")
    private float protein;

    @Column(name = "water")
    private float water;

    @Column(name = "cholesterol")
    private float cholesterol;

    @CreationTimestamp
    @Column(name = "ingr_created")
    private LocalDateTime ingredientCreated;
}