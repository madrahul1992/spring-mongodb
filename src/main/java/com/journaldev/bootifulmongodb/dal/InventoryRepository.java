package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    Inventory findByItemName(String itemName);

}
