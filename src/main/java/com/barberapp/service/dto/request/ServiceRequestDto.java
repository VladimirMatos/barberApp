package com.barberapp.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ServiceRequestDto {
    @NotBlank(message = "Name is required")
    @Schema(description = "Service name", type = SchemaType.STRING, format = "XXXXX", example = "Recortada sencilla")
    private String name;
}
