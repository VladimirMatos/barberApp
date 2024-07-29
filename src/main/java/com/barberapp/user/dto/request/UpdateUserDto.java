package com.barberapp.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
public class UpdateUserDto  implements Serializable {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name", type = SchemaType.STRING, format = "XXXXX", example = "Test")
    private String name;

    @NotBlank(message = "Name is required")
    @Schema(description = "Lastname", type = SchemaType.STRING, format = "XXXXX", example = "Test")
    private String lastname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Schema(description = "Email", type = SchemaType.STRING, format = "XXXXX", example = "test@test.com")
    private String email;

    @NotBlank(message = "Phone is required")
    @Schema(description = "Phone number", type = SchemaType.STRING, format = "XXXXX", example = "800-000-0000")
    private String phoneNumber;

    private Boolean isBarber;
}
