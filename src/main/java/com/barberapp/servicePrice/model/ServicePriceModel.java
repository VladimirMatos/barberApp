package com.barberapp.servicePrice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@MongoEntity(collection = "servicesPrice")
@Data
public class ServicePriceModel {
    @JsonProperty("_id")
    public ObjectId id;
    private ObjectId userId;
    private ObjectId serviceId;
    private Double price;

}
