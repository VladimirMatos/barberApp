package com.barberapp.auth.service;

import com.barberapp.auth.dto.Request.CreateUserDto;
import com.barberapp.auth.dto.Request.LoginUserDto;
import com.barberapp.user.dto.response.UserResponseDto;
import com.barberapp.user.model.UserModel;
import com.barberapp.user.repository.UserRepository;
import com.barberapp.utils.Constants;
import com.barberapp.utils.MapperSource;
import com.barberapp.utils.PasswordEncode;
import com.barberapp.utils.ResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ApplicationScoped
public class AuthService implements IAuthService{


    UserRepository userRepository;
    PasswordEncode passwordEncode;
    MapperSource mapperSource;

    @Inject
    public AuthService(UserRepository userRepository, PasswordEncode passwordEncode, MapperSource mapperSource) {
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncode;
        this.mapperSource = mapperSource;
    }


    @Override
    public Response signup(CreateUserDto user) {

        UserModel userCreated = new UserModel();
        ResponseDto responseDto = new ResponseDto();
        UserModel userFind = userRepository.findByEmail(user.getEmail());

        responseDto.setMessage(Constants.USER_ALREADY_EXISTS);
        responseDto.setCode(Status.BAD_REQUEST.getStatusCode());


        if(userFind != null){
            return  Response.status(Status.BAD_REQUEST).entity(responseDto).build();
        }

        userCreated.setEmail(user.email);
        userCreated.setName(user.name);
        userCreated.setLastname(user.lastname);
        userCreated.setPassword(passwordEncode.encode(user.password));
        userCreated.setPhoneNumber(user.phoneNumber);
        userCreated.setIsBarber(user.isBarber);


        userRepository.persist(userCreated);

        return Response.status(Status.CREATED).entity(userCreated).build();
    }

    @Override
    public Response login(LoginUserDto user) {
        UserModel userFind = userRepository.findByEmail(user.getEmail());

        if(!userFind.getPassword().equals(passwordEncode.encode(user.getPassword()))){
            return  null;
        }

        return Response.ok(mapperSource.mapObject(userFind, UserResponseDto.class)).build();
    }
}
