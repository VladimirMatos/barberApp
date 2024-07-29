package com.barberapp.utils;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponseDto<E> {
    public Integer page;
    public Integer totalPages;
    public List<E> data;

}
