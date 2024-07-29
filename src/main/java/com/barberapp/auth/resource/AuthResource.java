package com.barberapp.auth.resource;

import com.barberapp.auth.dto.Request.CreateUserDto;
import com.barberapp.auth.dto.Request.LoginUserDto;
import com.barberapp.auth.service.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.InputStream;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/auth")
@Produces(APPLICATION_JSON)
public class AuthResource {

    AuthService authService;

    @Inject
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/signup")
    public Response signup(@Valid CreateUserDto user){
        return authService.signup(user);
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginUserDto user){
        return authService.login(user);
    }

}
