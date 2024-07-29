package com.barberapp.service.service;

import com.barberapp.service.dto.request.ServiceRequestDto;
import com.barberapp.service.dto.response.ServiceResponseDto;
import com.barberapp.service.model.ServiceModel;
import com.barberapp.service.repository.ServiceRepository;
import com.barberapp.user.repository.UserRepository;
import com.barberapp.utils.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.bson.types.ObjectId;

@ApplicationScoped
public class BarberServiceService implements IBarberServiceService {

    ServiceRepository serviceRepository;
    UserRepository userRepository;
    MapperSource mapperSource;


    @Inject
    public BarberServiceService( MapperSource mapperSource, ServiceRepository serviceRepository, UserRepository userRepository) {
        this.mapperSource = mapperSource;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }



    @Override
    public PaginationResponseDto<ServiceResponseDto> getAllService(int page, int totalResult) {
        PaginationResponse<ServiceModel> services = serviceRepository.findAllWithPagination(page,totalResult);

        return mapperSource.mapPagination(services, ServiceResponseDto.class);
    }

    @Override
    public Response getOneById(ObjectId id) {
        ServiceModel serviceFind = serviceRepository.findOneById(id);
        ResponseDto responseDto = new ResponseDto();

        if(serviceFind == null){
            responseDto.setMessage(Constants.SERVICE_NOT_FOUND);
            responseDto.setCode(Status.NOT_FOUND.getStatusCode());
            return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        return Response.ok(mapperSource.mapObject(serviceFind, ServiceResponseDto.class)).build();
    }

    @Override
    public Response createService(ServiceRequestDto service) {

            String nameUpper = service.getName().substring(0,1).toUpperCase() + service.getName().substring(1);
            ServiceModel serviceFind = serviceRepository.findByName(nameUpper);

            if (serviceFind != null) {
                return Response.notModified().build();
            }

            ServiceModel serviceModel = new ServiceModel();

            serviceModel.setName(nameUpper);

            serviceRepository.persist(serviceModel);

            return Response.ok(mapperSource.mapObject(serviceModel, ServiceResponseDto.class)).build();

    }

}
