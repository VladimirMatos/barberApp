package com.barberapp.servicePrice.service;

import com.barberapp.servicePrice.dto.request.ServicePriceRequestDto;
import com.barberapp.servicePrice.dto.response.ServicePriceResponseDto;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@ApplicationScoped
public interface IServicePriceService {
    PaginationResponseDto<ServicePriceResponseDto> getAllService(int page, int totalResult);
    Response getOneById (ObjectId id);
    Response createServicePrice(ServicePriceRequestDto category);
}
