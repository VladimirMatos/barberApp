package com.barberapp.hoursSchedule.resource;

import com.barberapp.hoursSchedule.service.HoursScheduleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/HourSchedule")
public class HoursScheduleResource {

    HoursScheduleService hoursScheduleService;

    @Inject
    public HoursScheduleResource(HoursScheduleService hoursScheduleService) {
        this.hoursScheduleService = hoursScheduleService;
    }

    @GET
    public Response getAllHours(){
        return Response.ok(hoursScheduleService.getAllHoursSchedule()).build();
    }
}
