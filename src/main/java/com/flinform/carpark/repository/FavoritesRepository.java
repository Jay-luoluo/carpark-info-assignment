package com.flinform.carpark.repository;

import com.flinform.carpark.entity.CarPark;
import com.flinform.carpark.entity.UserFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository  extends JpaRepository<UserFavorites, Long> {
//      save(UserFavorites favorite);

    List<CarPark> findAllByUserId(Long userId);
}
