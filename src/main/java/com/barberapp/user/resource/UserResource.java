package com.barberapp.user.resource;

import com.barberapp.user.dto.request.UpdateUserDto;
import com.barberapp.user.dto.response.UserResponseDto;
import com.barberapp.user.service.UserService;
import com.barberapp.utils.PaginationResponseDto;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Comment;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.IOException;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.STRING;

@Path("/user")
@Produces(APPLICATION_JSON)
public class UserResource {

    UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @Schema(type = STRING, format = "binary")
    public static class UploadItemSchema {

    }

    @GET
    public Response getAllUser(@QueryParam("page") @DefaultValue("1") Integer page, @DefaultValue("5") @QueryParam("totalResult") Integer totalResult){

        PaginationResponseDto<UserResponseDto> users = userService.getAllUser(page, totalResult);

        return Response.ok(users).build();

    }

    @GET
    @Path("/{id}")
    public Response getOneUserById(@PathParam("id") String id){
       return userService.getOneUserById(new ObjectId(id));
    }

    @POST
    @Path("/image/{id}")
    @Consumes(MULTIPART_FORM_DATA)
    public Response uploadImageUser(@PathParam("id") String id, @RestForm("image") @Schema(implementation = UploadItemSchema.class) FileUpload image) throws IOException {
        try {

        return userService.uploadUserImage(image, new ObjectId(id));

        }catch (IOException ignored){
            return null;
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, @Valid UpdateUserDto user){
        return userService.updateUser(new ObjectId(id), user);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id){
        return  userService.deleteUser(new ObjectId(id));
    }
}
