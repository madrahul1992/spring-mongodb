package com.journaldev.bootifulmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.*;

// TODO: Calculate the order total

@Document
public class Order {

    @Id
    private String orderId;

    @NotNull
    private String email; // TODO: use accound id instead of email

    @NotNull
    private List<Item> items = new ArrayList<>();

    private int orderTotal; //TODO: Change it to bigdecimal or Long

    private Date creationDate = new Date();


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", email='" + email + '\'' +
                ", items=" + items +
                ", orderTotal=" + orderTotal +
                ", creationDate=" + creationDate +
                '}';
    }
}
