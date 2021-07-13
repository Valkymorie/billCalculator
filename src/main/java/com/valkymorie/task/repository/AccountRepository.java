package com.valkymorie.task.repository;

import com.valkymorie.task.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String >{


    List<Account> findAllByName(String name);

    Optional<Account> findByName(String name);
}
