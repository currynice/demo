package com.cxy.demo.demomongo.repository;


import com.cxy.demo.demomongo.entity.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "coffee",path = "coffee")
public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
