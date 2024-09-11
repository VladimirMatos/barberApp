package com.barberapp.hoursSchedule.service;

import com.barberapp.hoursSchedule.model.HoursScheduleModel;
import com.barberapp.hoursSchedule.repository.HoursScheduleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.List;

@ApplicationScoped
public class HoursScheduleService implements IHoursScheduleService{

    HoursScheduleRepository hoursScheduleRepository;

    @Inject
    public HoursScheduleService(HoursScheduleRepository hoursScheduleRepository) {
        this.hoursScheduleRepository = hoursScheduleRepository;
    }

    @Override
    public List<HoursScheduleModel> getAllHoursSchedule() {
        return hoursScheduleRepository.findAll().stream().toList();
    }
}
