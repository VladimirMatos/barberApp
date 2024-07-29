package com.barberapp.servicePrice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ServicePriceRequestDto {

    @NotBlank(message = "userId is required")
    @Schema(description = "User id", type = SchemaType.STRING, format = "XXXXX", example = "66a7b4e3b3441c464fed21ed")
    private String userId;

    @NotBlank(message = "Service is required")
    @Schema(description = "Service id", type = SchemaType.STRING, format = "XXXXX", example = "66a7b4e3b3441c464fed21ed")
    private String serviceId;

   @Min(value = 1, message = "Min price is 1")
    private Double price;
}
