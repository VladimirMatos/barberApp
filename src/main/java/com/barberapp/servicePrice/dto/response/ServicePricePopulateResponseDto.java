package com.barberapp.servicePrice.dto.response;

import com.barberapp.service.dto.response.ServiceResponseDto;
import com.barberapp.user.dto.response.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class ServicePricePopulateResponseDto {
    @JsonProperty("_id")
    public ObjectId id;
    private UserResponseDto user;
    private List<ServiceResponseDto> service;
    private Double price;
}
