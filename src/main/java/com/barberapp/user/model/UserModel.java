package com.barberapp.user.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "users")
public class UserModel {
    @JsonProperty("_id")
    public ObjectId id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean isBarber;
    private ImageModel image;
}
