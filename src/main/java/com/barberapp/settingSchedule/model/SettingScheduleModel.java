package com.barberapp.settingSchedule.model;

import com.barberapp.utils.Days;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@MongoEntity(collection = "settingSchedule")
public class SettingScheduleModel {
    @JsonProperty("_id")
    public ObjectId id;
    private ObjectId barberId;
    private Days days;
    private List<ObjectId> datesId;
}
