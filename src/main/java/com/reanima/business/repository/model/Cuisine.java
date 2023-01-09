package com.reanima.business.repository.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cuisine")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cuisine_id")
    private int cuisineId;

    @Column(name="cuisine_name")
    private String cuisineName;

}
