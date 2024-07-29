package com.barberapp.servicePrice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ServicePriceResponseDto {
    @JsonProperty("_id")
    public ObjectId id;
    private ObjectId userId;
    private ObjectId serviceId;
    private Double price;
}
