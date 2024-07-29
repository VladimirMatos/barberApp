package com.barberapp.auth.dto.Request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
@ApplicationScoped
public class CreateUserDto implements Serializable {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name", type = SchemaType.STRING, format = "XXXXX", example = "NameTest")
    public String name;


    @NotBlank(message = "Lastname is required")
    @Schema(description = "Lastname", type = SchemaType.STRING, format = "XXXXX", example = "LastTest")
    public String lastname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Schema(description = "Email", type = SchemaType.STRING, format = "XXXXX", example = "test@test.com")
    public String email;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password", type = SchemaType.STRING, format = "XXXXX", example = "paswordTest")
    public String password;

    @NotBlank(message = "Phone number is required")
    @Schema(description = "Phone number", type = SchemaType.STRING, format = "XXXXX", example = "809-555-5555")
    public String phoneNumber;


    @Schema(description = "Name", type = SchemaType.BOOLEAN, format = "XXXXX", example = "false")
    public Boolean isBarber;

}
