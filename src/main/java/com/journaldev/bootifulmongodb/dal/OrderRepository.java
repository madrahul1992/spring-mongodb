package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> getAllByEmail(String email);
}
