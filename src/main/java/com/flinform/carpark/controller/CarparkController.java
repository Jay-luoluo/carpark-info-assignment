package com.flinform.carpark.controller;

import com.flinform.carpark.Dto.AddToFavoritesRequest;
import com.flinform.carpark.entity.CarPark;
import com.flinform.carpark.entity.CarParkSpecification;
import com.flinform.carpark.entity.ParkUser;
import com.flinform.carpark.entity.UserFavorites;
import com.flinform.carpark.repository.CarParkRepository;
import com.flinform.carpark.repository.FavoritesRepository;
import com.flinform.carpark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/api/carparks")
public class CarparkController {

    @Autowired
    private CarParkRepository carParkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    // Get all carparks with optional filters
    @GetMapping("/carparks")
    public List<CarPark> getCarParks(
            @RequestParam(required = false) String freeParking,
            @RequestParam(required = false) String nightParking,
            @RequestParam(required = false) Double gantryHeight) {
        if (freeParking == null && nightParking == null && gantryHeight == null) {
            return carParkRepository.findAll();
        }
        //if it is necessary to filter,u need to add pageInfo;
        return carParkRepository.findAll(new CarParkSpecification(freeParking, nightParking, gantryHeight));
    }

    // Add a carpark to favorites
    @PostMapping("/favorites")
    public ResponseEntity<?> addToFavorites(@RequestBody AddToFavoritesRequest request) {
        process(request);
        return ResponseEntity.ok("Carpark added to favorites");
    }


    // Get all favorite carparks for a user
    @GetMapping("/favorites/{userId}")
    public List<CarPark> getUserFavorites(@PathVariable Long userId) {
        return favoritesRepository.findAllByUserId(userId);
    }

    private void process(AddToFavoritesRequest request) {
        UserFavorites favorite = new UserFavorites();
        // Assuming user and carpark exist.
        ParkUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User with ID " + request.getUserId() + " not found"));
        favorite.setUser(Objects.isNull(user) ? null : user);
        CarPark carpark = carParkRepository.findById(request.getCarparkId())
                .orElseThrow(() -> new NoSuchElementException("Carpark with ID " + request.getCarparkId() + " not found"));
        favorite.setCarpark(Objects.isNull(carpark) ? null : carpark);
        favorite.setAddedOn(LocalDateTime.now());
        favoritesRepository.save(favorite);
    }

}

