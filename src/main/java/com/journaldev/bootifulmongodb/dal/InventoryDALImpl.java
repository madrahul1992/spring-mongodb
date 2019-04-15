package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.model.Inventory;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDALImpl implements InventoryDAL{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Inventory addProduct(Inventory inventory) {
        Query query = new Query(Criteria.where("itemName").is(inventory.getItemName()));
//        BasicDBObject dbObject = new BasicDBObject("qty", inventory.getQty());
//        Update update =  Update.fromDBObject(BasicDBObjectBuilder.start("$inc", inventory).get());
        Update update = new Update();
        update.set("itemName", inventory.getItemName());
        update.inc("qty", inventory.getQty());
        update.set("price", inventory.getPrice());
        mongoTemplate.upsert(query, update, Inventory.class);
        return mongoTemplate.findOne(query, Inventory.class);
    }

    @Override
    public Inventory updateInventory(String itemName, int qty) {
        Query query =  new Query( Criteria.where("itemName")
                        .is(itemName)
                        .and("qty")
                        .gte(qty));
        Update update = new Update();
        update.inc("qty", -qty);
        Inventory inventory;
        synchronized (this){
            inventory = mongoTemplate.findAndModify(query, update, Inventory.class);
        }
        return inventory;
    }
}
