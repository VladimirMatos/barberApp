package com.barberapp.user.service;

import com.barberapp.auth.service.AuthService;
import com.barberapp.user.dto.request.UpdateUserDto;
import com.barberapp.user.dto.response.UserResponseDto;
import com.barberapp.user.model.ImageModel;
import com.barberapp.user.model.UserModel;
import com.barberapp.user.repository.UserRepository;
import com.barberapp.utils.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.UUID;

@ApplicationScoped
public class UserService implements IUserService{


    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    UserRepository userRepository;
    MapperSource mapperSource;
    FirebaseResource firebaseResource;

    @Inject
    public UserService(UserRepository userRepository, MapperSource mapperSource, FirebaseResource firebaseResource) {
        this.userRepository = userRepository;
        this.mapperSource = mapperSource;
        this.firebaseResource = firebaseResource;
    }

    @Override
    public PaginationResponseDto<UserResponseDto> getAllUser(Integer page, Integer totalPage) {
        PaginationResponse<UserModel> users = userRepository.findAllWithPagination(page, totalPage);

        return mapperSource.mapPagination(users, UserResponseDto.class);
    }

    @Override
    public Response getOneUserById(ObjectId id) {
        UserModel userFind = userRepository.findOneById(id);
        ResponseDto responseDto = new ResponseDto();

        responseDto.setMessage(Constants.USER_NOT_FOUND);
        responseDto.setCode(Status.NOT_FOUND.getStatusCode());

        if(userFind == null){
            return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        return Response.ok(mapperSource.mapObject(userFind, UserResponseDto.class)).build();
    }

    @Override
    public Response uploadUserImage(FileUpload image, ObjectId id) throws IOException {


       UserModel userModel = userRepository.findOneById(id);
       ResponseDto responseDto = new ResponseDto();
       ImageModel imageModel = new ImageModel();
       UUID uuid = UUID.randomUUID();
       String imageName = image.fileName() + uuid;
       String url =  firebaseResource.uploadImage(image,imageName);


       if(userModel == null){
           responseDto.setMessage(Constants.USER_NOT_FOUND);
           responseDto.setCode(404);
           return Response.status(Status.NOT_FOUND).entity(responseDto).build();
       }

       imageModel.setUrl(url);
       imageModel.setImageName(imageName);
       imageModel.setImageType(image.contentType());

       userModel.setImage(imageModel);

       responseDto.setMessage(url);
       responseDto.setCode(200);

       userRepository.update(userModel);

        return Response.ok(responseDto).build();
    }

    @Override
    public Response updateUser(ObjectId id, UpdateUserDto user) {

        ResponseDto responseDto = new ResponseDto();
        UserModel userFind = userRepository.findOneById(id);

        if(userFind == null){
            responseDto.setMessage(Constants.USER_NOT_FOUND);
            responseDto.setCode(404);
            return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        userFind.setName(user.getName());
        userFind.setEmail(user.getEmail());
        userFind.setLastname(user.getLastname());
        userFind.setPhoneNumber(user.getPhoneNumber());
        userFind.setIsBarber(user.getIsBarber());

        userRepository.update(userFind);

        return Response.ok(mapperSource.mapObject(userFind, UserModel.class)).build();
    }

    @Override
    public Response deleteUser(ObjectId id) {
        ResponseDto responseDto = new ResponseDto();
        UserModel userFind = userRepository.findOneById(id);

        if(userFind == null){
            responseDto.setMessage(Constants.USER_NOT_FOUND);
            responseDto.setCode(404);
            return Response.status(Status.NOT_FOUND).entity(responseDto).build();
        }

        if(userFind.getImage() != null){
            firebaseResource.deleteImage(userFind.getImage().getImageName());
        }

        userRepository.delete("_id", id);

        responseDto.setMessage("User deleted");
        responseDto.setCode(200);

        return  Response.ok(responseDto).build();
    }
}
