package com.reanima.business.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dish")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dish_id")
    private int dishId;

    @Column(name="dish_name")
    private String dishName;
}
