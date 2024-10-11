package com.flinform.carpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_park")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carParkNo;
    private String address;
    private double xCoord;
    private double yCoord;
    private String carParkType;
    private String typeOfParkingSystem;
    private String shortTermParking;
    private String freeParking;
    private String nightParking;
    private int carParkDecks;
    private double gantryHeight;
    private String carParkBasement;

//    CarParkSpecification specification;

}

