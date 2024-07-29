package com.barberapp.utils;

import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public record PaginationResponse<E> (int pages, int totalPages, List<E> data) {


    public PaginationResponse(PanacheQuery<E> query){
        this( query.page().index + 1, query.pageCount() ,query.list() );
    }

}
