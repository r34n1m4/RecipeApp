package com.reanima.business.repository.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder


public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingr_id")
    private int id;

    @Column(name = "ingr_name")
    private String ingredientName;

    @Column(name = "type")
    private String ingredientType;

    @Column(name = "ingr_desc")
    private String ingredientDescription;

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
}
