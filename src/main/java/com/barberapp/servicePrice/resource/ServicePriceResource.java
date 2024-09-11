package com.barberapp.servicePrice.resource;

import com.barberapp.servicePrice.dto.request.ServicePriceRequestDto;
import com.barberapp.servicePrice.dto.request.UpdatePriceDto;
import com.barberapp.servicePrice.dto.response.ServicePricePopulateResponseDto;
import com.barberapp.servicePrice.dto.response.ServicePriceResponseDto;
import com.barberapp.servicePrice.service.ServicePriceService;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/servicePrice")
@Produces(APPLICATION_JSON)
public class ServicePriceResource {

    ServicePriceService servicePriceService;

    @Inject
    public ServicePriceResource(ServicePriceService servicePriceService) {
        this.servicePriceService = servicePriceService;
    }

    @GET
    public Response getAllService(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("totalResult") @DefaultValue("5") int totalResult){
        PaginationResponseDto<ServicePricePopulateResponseDto>  services =  servicePriceService.getAllService(page, totalResult);

        return  Response.ok().entity(services).build();
    }

    @GET
    @Path("/getAllByUser")
    public Response getAllServiceByUser(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("totalResult") @DefaultValue("5") int totalResult, @QueryParam("userId") String userId){
        return servicePriceService.getServicePriceByUserId(page, totalResult, new ObjectId(userId));
    }


    @GET
    @Path("/{id}")
    public Response getOneService(@PathParam("id") String id){

        return servicePriceService.getOneById(new ObjectId(id));

    }

    @POST
    public Response createServicePrice(@Valid ServicePriceRequestDto service){

        return servicePriceService.createServicePrice(service);

    }

    @DELETE
    @Path("/{id}")
    public Response deleteServicePrice(@QueryParam("id") String id ){

        return servicePriceService.deleteServicePrice(new ObjectId(id));

    }

    @PUT
    @Path("/{id}")
    public Response updateServicePrice(@QueryParam("id") String id, @Valid UpdatePriceDto service ){

        return servicePriceService.updateServicePrice(service,new ObjectId(id));

    }
}
