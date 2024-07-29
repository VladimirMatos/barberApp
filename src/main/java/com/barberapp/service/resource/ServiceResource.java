package com.barberapp.service.resource;

import com.barberapp.service.dto.request.ServiceRequestDto;
import com.barberapp.service.dto.response.ServiceResponseDto;
import com.barberapp.service.service.BarberServiceService;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/service")
@Produces(APPLICATION_JSON)
public class ServiceResource {

    BarberServiceService barberServiceService;

    @Inject
    public ServiceResource(BarberServiceService barberServiceService) {
        this.barberServiceService = barberServiceService;
    }

    @GET
    public Response getAllService(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("totalResult") @DefaultValue("5") int totalResult){
        PaginationResponseDto<ServiceResponseDto> services =  barberServiceService.getAllService(page, totalResult);

        return  Response.ok().entity(services).build();
    }

    @GET
    @Path("/{id}")
    public Response getOneService(@PathParam("id") String id){

        return barberServiceService.getOneById(new ObjectId(id));

    }

    @POST
    public Response createService(@Valid ServiceRequestDto category){

        return barberServiceService.createService(category);

    }
}
