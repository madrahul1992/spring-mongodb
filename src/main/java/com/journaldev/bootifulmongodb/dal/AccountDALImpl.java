package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDALImpl implements AccountDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Override
//    public Account getAccountByEmail(String email) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("email").is(email));
//        return mongoTemplate.findOne(query, Account.class);
//    }

    @Override
    public Account addNewAccount(Account account) {
        mongoTemplate.save(account);
        // Now, user object will contain the ID as well
        return account;
    }
}
