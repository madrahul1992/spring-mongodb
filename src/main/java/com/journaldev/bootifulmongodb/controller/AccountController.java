package com.journaldev.bootifulmongodb.controller;

import com.journaldev.bootifulmongodb.dal.AccountDAL;
import com.journaldev.bootifulmongodb.dal.AccountRepository;
import com.journaldev.bootifulmongodb.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final AccountRepository accountRepository;

    private final AccountDAL accountDAL;

    public AccountController(AccountRepository accountRepository, AccountDAL accountDAL) {
        this.accountRepository = accountRepository;
        this.accountDAL = accountDAL;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Account createAccount(@Valid @RequestBody Account account) {
        LOG.info("Inside create Order.");
        return accountDAL.addNewAccount(account);
//        return order;
    }
}
