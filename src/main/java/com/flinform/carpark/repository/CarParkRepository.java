package com.flinform.carpark.repository;

import com.flinform.carpark.entity.CarPark;
import com.flinform.carpark.entity.CarParkSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarParkRepository extends JpaRepository<CarPark, Long>, JpaSpecificationExecutor<CarPark> {


}

