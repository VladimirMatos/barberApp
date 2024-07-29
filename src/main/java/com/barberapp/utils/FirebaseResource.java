package com.barberapp.utils;


import com.google.cloud.storage.*;
import jakarta.enterprise.context.RequestScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;


@RequestScoped
public class FirebaseResource {
    private static final Logger LOG = LoggerFactory.getLogger(FirebaseResource.class);
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    @ConfigProperty(name = "com.barberapp.firebase.bucket")
    private String bucket;



    public String uploadImage(FileUpload image, String name) throws IOException {


        byte[] fileBytes = Files.readAllBytes(image.uploadedFile());
        BlobId blobId = BlobId.of(bucket, name);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(image.contentType()).build();

        // Upload the file to Firebase Storage
        Blob upload = storage.create(blobInfo, fileBytes);



        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", upload.getBucket(), name);

    }

    public Boolean deleteImage (String imageName){

        BlobId blobId = BlobId.of(bucket, imageName);

       return storage.delete(blobId);

    }
}
