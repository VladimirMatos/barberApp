package com.barberapp.service.service;

import com.barberapp.service.dto.request.ServiceRequestDto;
import com.barberapp.service.dto.response.ServiceResponseDto;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@ApplicationScoped
public interface IBarberServiceService {
    PaginationResponseDto<ServiceResponseDto> getAllService(int page, int totalResult);
    Response getOneById (ObjectId id);
    Response createService(ServiceRequestDto category);
}
