package com.reanima.business.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    private String recipe_name;
    private String recipe_desc;
    private String recipe_prep;
    private String cousine_type;
    private String dish_type;

}
