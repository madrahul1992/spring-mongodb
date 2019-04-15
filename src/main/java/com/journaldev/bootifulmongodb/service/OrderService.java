package com.journaldev.bootifulmongodb.service;

import com.journaldev.bootifulmongodb.dal.AccountRepository;
import com.journaldev.bootifulmongodb.dal.InventoryDAL;
import com.journaldev.bootifulmongodb.dal.InventoryRepository;
import com.journaldev.bootifulmongodb.dal.OrderRepository;
import com.journaldev.bootifulmongodb.model.Account;
import com.journaldev.bootifulmongodb.model.Inventory;
import com.journaldev.bootifulmongodb.model.Item;
import com.journaldev.bootifulmongodb.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    InventoryDAL inventoryDAL;

    @Autowired
    OrderRepository orderRepository;

    public Order validateAndCreateOrderObject(Order order) throws Exception {
        LOG.info("Account owner"+  order.getEmail());
        Account account = getAccount(order.getEmail());
        LOG.info("account:: "+ account);
        int orderTotal = 0;
        if(account != null){
            List<Item> items = order.getItems();
            if(items.size() > 0){
                for(Item item: items){
                    Inventory inventory = inventoryDAL.updateInventory(item.getItemName(), item.getQty());
                    if(inventory != null){
                        orderTotal += inventory.getPrice();
                    }else {
                        throw new Exception(item.getItemName() + " is out of stock");
                    }
                }
                order.setOrderTotal(orderTotal);
//                order = orderRepository.createOrder(order);
            } else {
                throw new Exception("Please add items to the order");
            }
        } else {
            throw new Exception("User is not found");
        }
        return order;
    }

    private Account getAccount(String email) {
        LOG.info("Email is:: "+ email);
        Account account =  accountRepository.findAccountByEmail(email);
        LOG.info("Account:: " + account);
        return accountRepository.findAccountByEmail(email);
    }


}
