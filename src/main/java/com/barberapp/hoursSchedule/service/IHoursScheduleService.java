package com.barberapp.hoursSchedule.service;

import com.barberapp.hoursSchedule.model.HoursScheduleModel;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface IHoursScheduleService {
    List<HoursScheduleModel> getAllHoursSchedule();
}
