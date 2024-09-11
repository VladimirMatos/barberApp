package com.barberapp.servicePrice.model;


import com.barberapp.service.model.ServiceModel;
import com.barberapp.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;



@MongoEntity(collection = "servicesPrice")
@Data
public class ServicePriceModel {
    @JsonProperty("_id")
    public ObjectId id;
    private ObjectId userId;
    private UserModel userModel;
    private ObjectId serviceId;
    private ServiceModel serviceModel;
    private Double price;

}
