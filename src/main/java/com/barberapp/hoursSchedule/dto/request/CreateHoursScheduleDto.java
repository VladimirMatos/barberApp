package com.barberapp.hoursSchedule.dto.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateHoursScheduleDto {
    private LocalTime startHr;
    private LocalTime endHr;
    private Boolean isPicked = false;
}
