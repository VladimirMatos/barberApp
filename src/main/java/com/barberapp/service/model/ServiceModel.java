package com.barberapp.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;


@Data
@MongoEntity(collection = "services")
public class ServiceModel {
    @JsonProperty("_id")
    public ObjectId id;
    private String name;

}
