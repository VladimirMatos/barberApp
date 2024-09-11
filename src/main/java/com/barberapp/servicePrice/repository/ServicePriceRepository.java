package com.barberapp.servicePrice.repository;

import com.barberapp.servicePrice.model.ServicePriceModel;
import com.barberapp.utils.GenericRepository;
import com.barberapp.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicePriceRepository implements GenericRepository<ServicePriceModel> {
    public ServicePriceModel findOneById(ObjectId id){
        return find("_id", id).firstResult();
    }
    public List<ServicePriceModel> findWithPopulate(int pages, int totalResult) {

        int skip = (pages - 1) * totalResult;

        List<Document> servicePriceStage = List.of(new Document("$lookup",
                        new Document("from", "users")
                                .append("localField", "userId")
                                .append("foreignField", "_id")
                                .append("as", "userModel")),
                new Document("$unwind","$userModel"),
                new Document("$lookup",
                        new Document("from", "services")
                                .append("localField", "serviceId")
                                .append("foreignField", "_id")
                                .append("as", "serviceModel")),
                new Document("$unwind","$serviceModel"),
                new Document("$skip", skip),
                new Document("$limit", totalResult)
                );

        return mongoCollection().aggregate(servicePriceStage).into(new ArrayList<>());

    }
    public List<ServicePriceModel> findModelByUser(int pages, int totalResult, ObjectId userId){
        int skip = (pages - 1) * totalResult;

        List<Document> servicePriceStage = List.of(
                new Document("$match",
                                new Document("userId",
                                        userId)),
                        new Document("$lookup",
                                new Document("from", "users")
                                        .append("localField", "userId")
                                        .append("foreignField", "_id")
                                        .append("as", "userModel")),
                        new Document("$unwind",
                                new Document("path", "$userModel")),
                        new Document("$lookup",
                                new Document("from", "services")
                                        .append("localField", "serviceId")
                                        .append("foreignField", "_id")
                                        .append("as", "serviceModel")),
                        new Document("$unwind",
                                new Document("path", "$serviceModel")),
                new Document("$skip", skip),
                new Document("$limit", totalResult)
        );

        return mongoCollection().aggregate(servicePriceStage).into(new ArrayList<>());

    }
}
