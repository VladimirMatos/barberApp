package com.barberapp.settingSchedule.dto.request;

import com.barberapp.utils.Days;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalTime;

@Data
public class CreateSettingScheduleDto {
    private Days day;
    private LocalTime startHr;
    private LocalTime endHr;
    private Integer intervals;
}
