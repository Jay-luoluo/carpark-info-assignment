package com.flinform.carpark.entity;

import lombok.Data;
import javax.persistence.*;


@Entity
@Table(name = "park_user")
@Data
public class ParkUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

}
