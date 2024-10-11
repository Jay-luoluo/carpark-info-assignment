package com.flinform.carpark.repository;

import com.flinform.carpark.entity.ParkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ParkUser, Long> {
    Optional<ParkUser> findById(Long userId);
}
