package com.barberapp.servicePrice.repository;

import com.barberapp.servicePrice.model.ServicePriceModel;
import com.barberapp.utils.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ServicePriceRepository implements GenericRepository<ServicePriceModel> {
    public ServicePriceModel findByName(String name){
        return find("name", name).firstResult();
    }
    public ServicePriceModel findOneById(ObjectId id){
        return find("_id", id).firstResult();
    }

}
