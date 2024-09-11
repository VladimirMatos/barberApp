package com.barberapp.servicePrice.service;

import com.barberapp.service.model.ServiceModel;
import com.barberapp.service.repository.ServiceRepository;
import com.barberapp.servicePrice.dto.request.ServicePriceRequestDto;
import com.barberapp.servicePrice.dto.request.UpdatePriceDto;
import com.barberapp.servicePrice.dto.response.ServicePricePopulateResponseDto;
import com.barberapp.servicePrice.dto.response.ServicePriceResponseDto;
import com.barberapp.servicePrice.mapper.ServicePriceMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class ServicePriceService implements IServicePriceService {

    private static final Logger LOG = LoggerFactory.getLogger(ServicePriceService.class);
    ServiceRepository serviceRepository;
    ServicePriceRepository servicePriceRepository;
    UserRepository userRepository;
    MapperSource mapperSource;
    ServicePriceMapper servicePriceMapper;

    @Inject
    public ServicePriceService(MapperSource mapperSource, ServicePriceRepository servicePriceRepository, UserRepository userRepository, ServiceRepository serviceRepository, ServicePriceMapper servicePriceMapper) {
        this.mapperSource = mapperSource;
        this.servicePriceRepository = servicePriceRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.servicePriceMapper = servicePriceMapper;
    }



    @Override
    public PaginationResponseDto<ServicePricePopulateResponseDto> getAllService(int page, int totalResult) {

        List<ServicePriceModel> servicePrice = servicePriceRepository.findWithPopulate(page, totalResult);

        long totalCount = servicePriceRepository.count();
        int totalPages = (int) Math.ceil((double) totalCount / totalResult);

        return servicePriceMapper.mapServicePricePopulateWithPagination(servicePrice, page, totalPages, ServicePricePopulateResponseDto.class);

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

    @Override
    public Response getServicePriceByUserId(int page, int totalResult, ObjectId userId) {

        List<ServicePriceModel> servicePrice = servicePriceRepository.findModelByUser(page, totalResult, userId);

        long totalCount = servicePrice.isEmpty() ? 0 : servicePriceRepository.count();
        int totalPages = (int) Math.ceil((double) totalCount / totalResult);

        return Response.ok(servicePriceMapper.mapServicePricePopulateWithPagination(servicePrice, page, totalPages, ServicePricePopulateResponseDto.class)).build();
    }

    @Override
    public Response deleteServicePrice(ObjectId id) {
        ResponseDto responseDto = new ResponseDto();
        ServicePriceModel serviceModel = servicePriceRepository.findOneById(id);

        if(serviceModel == null){
            responseDto.setMessage(Constants.SERVICE_PRICE_NOT_FOUND);
            responseDto.setCode(Status.NOT_FOUND.getStatusCode());

           return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        servicePriceRepository.delete("_id", id);
        responseDto.setMessage("Service price deleted");
        responseDto.setCode(Status.OK.getStatusCode());

        return Response.ok(responseDto).build();
    }

    @Override
    public Response updateServicePrice(UpdatePriceDto service, ObjectId id) {
        ResponseDto responseDto = new ResponseDto();
        ServicePriceModel serviceModel = servicePriceRepository.findOneById(id);

        if(serviceModel == null){
            responseDto.setMessage(Constants.SERVICE_PRICE_NOT_FOUND);
            responseDto.setCode(Status.NOT_FOUND.getStatusCode());

            return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        serviceModel.setPrice(service.getPrice());

        servicePriceRepository.update(serviceModel);

        return Response.ok(serviceModel).build();

    }


}
