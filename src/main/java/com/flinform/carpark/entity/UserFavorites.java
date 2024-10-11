package com.flinform.carpark.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_favorites")
@Data
public class UserFavorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parkuser_id", nullable = false)
    private ParkUser user;  // Assuming a User entity exists.

    @ManyToOne
    @JoinColumn(name = "carpark_id", nullable = false)
    private CarPark carpark;

    private LocalDateTime addedOn;

}

