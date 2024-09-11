package com.barberapp.utils;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponseDto<E> {
    private Integer page;
    private Integer totalPages;
    private List<E> data;

}
