package com.journaldev.bootifulmongodb.dal;

import java.util.List;

import com.journaldev.bootifulmongodb.model.Account;
import com.journaldev.bootifulmongodb.model.Item;
import com.journaldev.bootifulmongodb.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.model.Order;

@Repository
public class OrderDALImpl implements OrderDAL{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Override
//    public List<Order> getAllOrders(){
//        return mongoTemplate.findAll(Order.class);
//    }

//    @Override
//    public Order getOrderById(String orderId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("orderId").is(orderId));
//        return mongoTemplate.findOne(query, Order.class);
//    }

    /**
     * This mehod will validate input parameters and create order
     * We can also validate params here for better error handling
     * @param order
     * @return
     */

    @Override
    public Order createOrder(Order order) throws Exception {

        LOG.info("Inside Order creation input:: "+ order);

//        orderService = new OrderService();
        try{
            order = orderService.validateAndCreateOrderObject(order);

            mongoTemplate.save(order);
            // Now, user object will contain the ID as well
            return order;

        } catch (Exception e){
            LOG.info(e.getMessage());
            throw new Exception("Something went wrong while creating order:: "+ e.getMessage());
        }
    }


}
