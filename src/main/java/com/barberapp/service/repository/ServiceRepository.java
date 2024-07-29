package com.barberapp.service.repository;

import com.barberapp.service.model.ServiceModel;
import com.barberapp.utils.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ServiceRepository implements GenericRepository<ServiceModel> {
    public ServiceModel findByName(String name){
        return find("name", name).firstResult();
    }
    public ServiceModel findOneById(ObjectId id){
        return find("_id", id).firstResult();
    }
}
