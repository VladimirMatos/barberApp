package com.barberapp.servicePrice.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdatePriceDto {
    @Min(value = 1, message = "Min price is 1")
    private Double price;
}
