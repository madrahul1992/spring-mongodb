package com.journaldev.bootifulmongodb.controller;

import java.util.List;

import com.journaldev.bootifulmongodb.dal.OrderDAL;
import com.journaldev.bootifulmongodb.dal.OrderRepository;
import com.journaldev.bootifulmongodb.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final OrderRepository orderRepository;

    private final OrderDAL orderDAL;

    public OrderController(OrderDAL orderDAL, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderDAL = orderDAL;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrder(@Valid @RequestBody Order order) {
        LOG.info("Inside create Order.");
        try{
            orderDAL.createOrder(order);
        }catch (Exception e){
            LOG.error(e.getMessage());
            return e.getMessage();
        }
        return "Order created successfully";
//        return order;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        LOG.info("Getting all orders.");
        return orderRepository.findAll();
    }

}
