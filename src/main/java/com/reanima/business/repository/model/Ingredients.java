package com.reanima.business.repository.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Ingredients {

    @Id
    @GeneratedValue
    private int id;
    private String ingr_name;
    private String ingr_type;
    private String ingr_desc;
    private float calories;
    private float carbohydrates;
    private float fat;
    private float protein;
    private float water;
    private float cholesterol;


}
