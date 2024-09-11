package com.barberapp.utils;

import jakarta.enterprise.context.RequestScoped;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class MapperSource {
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> T mapObject(S source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(source, targetClass );
    }

    public <S,E> PaginationResponseDto<E> mapPagination(PaginationResponse<S> source, Class<E> targetClass) {
       PaginationResponseDto<E> paginationResponseDto = new PaginationResponseDto<>();

        List<E> data = mapList(source.data(), targetClass);

        paginationResponseDto.setPage(source.pages());
        paginationResponseDto.setTotalPages(source.totalPages());
        paginationResponseDto.setData(data);

        return paginationResponseDto;
    }

}
