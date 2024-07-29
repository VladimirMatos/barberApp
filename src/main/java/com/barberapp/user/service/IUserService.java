package com.barberapp.user.service;

import com.barberapp.user.dto.request.UpdateUserDto;
import com.barberapp.user.dto.response.UserResponseDto;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.IOException;

@ApplicationScoped
public interface IUserService {
     PaginationResponseDto<UserResponseDto> getAllUser(Integer page, Integer totalPage);
     Response getOneUserById(ObjectId id);
     Response uploadUserImage(FileUpload image, ObjectId id) throws IOException;
     Response updateUser(ObjectId id, UpdateUserDto user);
     Response deleteUser(ObjectId id);
}
