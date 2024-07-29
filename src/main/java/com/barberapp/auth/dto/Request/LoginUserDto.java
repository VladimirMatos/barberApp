package com.barberapp.auth.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class LoginUserDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Schema(description = "Email", type = SchemaType.STRING, format = "XXXXX", example = "test@test.com")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
