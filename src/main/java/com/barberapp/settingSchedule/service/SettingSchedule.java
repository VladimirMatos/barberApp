package com.barberapp.settingSchedule.service;

import com.barberapp.hoursSchedule.dto.request.CreateHoursScheduleDto;
import com.barberapp.hoursSchedule.model.HoursScheduleModel;
import com.barberapp.hoursSchedule.repository.HoursScheduleRepository;
import com.barberapp.settingSchedule.dto.request.CreateSettingScheduleDto;
import com.barberapp.settingSchedule.dto.response.DateHrsResponse;
import com.barberapp.settingSchedule.model.SettingScheduleModel;
import com.barberapp.settingSchedule.repository.SettingScheduleRepository;
import com.barberapp.user.service.UserService;
import com.barberapp.utils.MapperSource;
import com.barberapp.utils.PaginationResponse;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SettingSchedule implements ISettingSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    SettingScheduleRepository settingScheduleRepository;
    HoursScheduleRepository hoursScheduleRepository;
    MapperSource mapperSource;

    @Inject
    public SettingSchedule(SettingScheduleRepository settingScheduleRepository, MapperSource mapperSource, HoursScheduleRepository hoursScheduleRepository) {
        this.settingScheduleRepository = settingScheduleRepository;
        this.mapperSource = mapperSource;
        this.hoursScheduleRepository = hoursScheduleRepository;
    }

    @Override
    public PaginationResponseDto<DateHrsResponse> findAllSettingSchedule(int page, int totalResult) {
        PaginationResponse<SettingScheduleModel> dateHrs =  settingScheduleRepository.findAllWithPagination(page, totalResult);

        return mapperSource.mapPagination(dateHrs, DateHrsResponse.class);
    }

    @Override
    public Response createSettingSchule(CreateSettingScheduleDto setting, ObjectId barberId) {
        List<ObjectId> hoursBulk = new ArrayList<>();
        LocalTime currentTime = setting.getStartHr();
        SettingScheduleModel settingScheduleModel = new SettingScheduleModel();

        while (currentTime.isBefore(setting.getEndHr())){
            HoursScheduleModel createHoursScheduleDto = new HoursScheduleModel();
            createHoursScheduleDto.setStartHr(currentTime);
            currentTime = currentTime.plusMinutes(setting.getIntervals());
            createHoursScheduleDto.setEndHr(currentTime);
            hoursScheduleRepository.persist(createHoursScheduleDto);
            hoursBulk.add(createHoursScheduleDto.id);
        }

        settingScheduleModel.setBarberId(barberId);
        settingScheduleModel.setDays(setting.getDay());
        settingScheduleModel.setDatesId(hoursBulk);

        LOG.info("Id: {}", hoursBulk);

        settingScheduleRepository.persist(settingScheduleModel);


        return Response.ok(settingScheduleModel).build();
    }
}
