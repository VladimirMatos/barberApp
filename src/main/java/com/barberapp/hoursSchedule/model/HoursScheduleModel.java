package com.barberapp.hoursSchedule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalTime;

@Data
@MongoEntity(collection = "hourSchedule")
public class HoursScheduleModel {
    @JsonProperty("_id")
    public ObjectId id;
    private LocalTime startHr;
    private LocalTime endHr;
    private Boolean isPicked = false;
}
