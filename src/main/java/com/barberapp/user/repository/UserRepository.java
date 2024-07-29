package com.barberapp.user.repository;

import com.barberapp.user.model.UserModel;
import com.barberapp.utils.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class UserRepository implements GenericRepository<UserModel> {
    public UserModel findByEmail(String email){
        return find("email", email).firstResult();
    }

    public UserModel findOneById(ObjectId id){
        return find("_id", id).firstResult();
    }
}
