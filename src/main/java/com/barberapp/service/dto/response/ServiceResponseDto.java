package com.barberapp.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
public class ServiceResponseDto {
    @JsonProperty("_id")
    public ObjectId id;
    private String name;
}
