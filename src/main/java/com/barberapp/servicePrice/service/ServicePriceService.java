package com.barberapp.servicePrice.service;

import com.barberapp.service.model.ServiceModel;
import com.barberapp.service.repository.ServiceRepository;
import com.barberapp.servicePrice.dto.request.ServicePriceRequestDto;
import com.barberapp.servicePrice.dto.response.ServicePriceResponseDto;
import com.barberapp.servicePrice.model.ServicePriceModel;
import com.barberapp.servicePrice.repository.ServicePriceRepository;
import com.barberapp.user.model.UserModel;
import com.barberapp.user.repository.UserRepository;
import com.barberapp.utils.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ServicePriceService implements IServicePriceService {

    ServiceRepository serviceRepository;
    ServicePriceRepository servicePriceRepository;
    UserRepository userRepository;
    MapperSource mapperSource;

    @Inject
    public ServicePriceService(MapperSource mapperSource, ServicePriceRepository servicePriceRepository, UserRepository userRepository, ServiceRepository serviceRepository) {
        this.mapperSource = mapperSource;
        this.servicePriceRepository = servicePriceRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
    }



    @Override
    public PaginationResponseDto<ServicePriceResponseDto> getAllService(int page, int totalResult) {
        PaginationResponse<ServicePriceModel> services = servicePriceRepository.findAllWithPagination(page,totalResult);

        return mapperSource.mapPagination(services, ServicePriceResponseDto.class);
    }

    @Override
    public Response getOneById(ObjectId id) {
        ResponseDto responseDto = new ResponseDto();
        ServicePriceModel serviceFind = servicePriceRepository.findOneById(id);

        if(serviceFind == null){
            responseDto.setMessage(Constants.SERVICE_NOT_FOUND);
            responseDto.setCode(Status.NOT_FOUND.getStatusCode());
            return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        return Response.ok(mapperSource.mapObject(serviceFind, ServicePriceResponseDto.class)).build();
    }

    @Override
    public Response createServicePrice(ServicePriceRequestDto service) {

        ResponseDto responseDto = new ResponseDto();
        UserModel userFind = userRepository.findOneById(new ObjectId(service.getUserId()));
        ServiceModel serviceFind = serviceRepository.findOneById(new ObjectId(service.getServiceId()));
        ServicePriceModel servicePriceModel = new ServicePriceModel();

        if(userFind == null) {
            responseDto.setMessage(Constants.USER_NOT_FOUND);
            responseDto.setCode(Status.BAD_REQUEST.getStatusCode());
            return Response.status(Status.BAD_REQUEST).entity(responseDto).build();
        }

        if(serviceFind == null) {
            responseDto.setMessage(Constants.SERVICE_NOT_FOUND);
            responseDto.setCode(Status.BAD_REQUEST.getStatusCode());
            return Response.status(Status.BAD_REQUEST).entity(responseDto).build();
        }

        servicePriceModel.setUserId(new ObjectId(service.getUserId()));
        servicePriceModel.setServiceId(new ObjectId(service.getServiceId()));
        servicePriceModel.setPrice(service.getPrice());

        servicePriceRepository.persist(servicePriceModel);

        return Response.ok(mapperSource.mapObject(servicePriceModel, ServicePriceResponseDto.class)).build();

    }
}
