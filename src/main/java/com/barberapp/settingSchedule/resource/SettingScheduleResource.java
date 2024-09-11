package com.barberapp.settingSchedule.resource;


import com.barberapp.settingSchedule.dto.request.CreateSettingScheduleDto;
import com.barberapp.settingSchedule.dto.response.DateHrsResponse;
import com.barberapp.settingSchedule.service.SettingSchedule;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/SettingSchedule")
@Produces(APPLICATION_JSON)
public class SettingScheduleResource {

    SettingSchedule settingSchedule;

    @Inject
    public SettingScheduleResource(SettingSchedule settingSchedule) {

        this.settingSchedule = settingSchedule;
    }

    @GET
    public Response getAllSettingDates (@QueryParam("page") @DefaultValue("1") int page, @QueryParam("totalResult") @DefaultValue("5") int totalResult ){

        PaginationResponseDto<DateHrsResponse> dateHrsResponse = settingSchedule.findAllSettingSchedule(page,totalResult);

        return Response.ok(dateHrsResponse).build();

    }

    @POST
    @Path("/{id}")
    public Response createSettingHrs (@Valid CreateSettingScheduleDto setting, @QueryParam("id") String barberId){
        return settingSchedule.createSettingSchule(setting,new ObjectId(barberId));
    }
}
