package com.journaldev.bootifulmongodb.dal;

import java.util.List;

import com.journaldev.bootifulmongodb.model.Order;

public interface OrderDAL {
//    List<Order> getAllOrders();
//
//    Order getOrderById(String orderId);

    Order createOrder(Order order) throws Exception;

}
