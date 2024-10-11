package com.flinform.carpark.Dto;

import lombok.Data;

@Data
public class AddToFavoritesRequest {
    private Long userId;
    private Long carparkId;

}

