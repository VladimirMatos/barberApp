package com.barberapp.servicePrice.mapper;

import com.barberapp.service.dto.response.ServiceResponseDto;
import com.barberapp.service.model.ServiceModel;
import com.barberapp.servicePrice.dto.response.ServicePricePopulateResponseDto;
import com.barberapp.servicePrice.dto.response.ServicePriceResponseDto;
import com.barberapp.servicePrice.model.ServicePriceModel;
import com.barberapp.user.dto.response.UserResponseDto;
import com.barberapp.user.model.UserModel;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.RequestScoped;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ServicePriceMapper {

    public PaginationResponseDto<ServicePricePopulateResponseDto> mapServicePricePopulateWithPagination(List<ServicePriceModel> source, int page, int totalResult, Class<ServicePricePopulateResponseDto> targetClass) {

        PaginationResponseDto<ServicePricePopulateResponseDto> paginationResponseDto = new PaginationResponseDto<>();

        ModelMapper modelMapper = new ModelMapper();

        List<ServicePricePopulateResponseDto> dataMapper =  source
                .stream()
                .map(element ->   modelMapper.map(element, targetClass))
                .collect(Collectors.toList());


        paginationResponseDto.setPage(page);
        paginationResponseDto.setTotalPages(totalResult);
        paginationResponseDto.setData(dataMapper);

        return  paginationResponseDto;

    }
}
