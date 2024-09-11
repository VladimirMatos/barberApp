package com.barberapp.servicePrice.service;

import com.barberapp.servicePrice.dto.request.ServicePriceRequestDto;
import com.barberapp.servicePrice.dto.request.UpdatePriceDto;
import com.barberapp.servicePrice.dto.response.ServicePricePopulateResponseDto;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@ApplicationScoped
public interface IServicePriceService {
    PaginationResponseDto<ServicePricePopulateResponseDto> getAllService(int page, int totalResult);
    Response getOneById (ObjectId id);
    Response createServicePrice(ServicePriceRequestDto service);
    Response getServicePriceByUserId(int page, int totalResult, ObjectId userId);
    Response deleteServicePrice(ObjectId id);
    Response updateServicePrice(UpdatePriceDto service, ObjectId id);
}
