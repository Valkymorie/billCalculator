package com.valkymorie.task.repository;

import com.valkymorie.task.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findAllByName(String name);
    Optional<Item> findByName(String name);
}
