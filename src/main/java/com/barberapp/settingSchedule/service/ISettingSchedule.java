package com.barberapp.settingSchedule.service;

import com.barberapp.settingSchedule.dto.request.CreateSettingScheduleDto;
import com.barberapp.settingSchedule.dto.response.DateHrsResponse;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@ApplicationScoped
public interface ISettingSchedule {
    PaginationResponseDto<DateHrsResponse> findAllSettingSchedule(int page, int totalResult);
    Response createSettingSchule(CreateSettingScheduleDto setting, ObjectId barberId);
}
