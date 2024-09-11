package com.barberapp.appointment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
@MongoEntity(collection = "appointment")
public class AppointmentModel {
    @JsonProperty("_id")
    public ObjectId id;
    private ObjectId barberId;
    private ObjectId clientId;
    private ObjectId barberScheduleId;
    private Boolean isActive;
    private LocalDate createdAt;


}
