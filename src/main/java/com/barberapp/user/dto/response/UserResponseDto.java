package com.barberapp.user.dto.response;

import com.barberapp.user.model.ImageModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UserResponseDto {
    @JsonProperty("_id")
    public ObjectId id;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;
    private ImageModel image;
    private Boolean isBarber;
}
