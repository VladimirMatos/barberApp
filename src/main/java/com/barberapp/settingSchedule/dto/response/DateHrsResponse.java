package com.barberapp.settingSchedule.dto.response;

import com.barberapp.utils.Days;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class DateHrsResponse {
    @JsonProperty("_id")
    private ObjectId id;
    private ObjectId barberId;
    private Days days;
    private ObjectId dateScheduledId;
}
