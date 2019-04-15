package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.model.Account;

import java.util.List;

public interface AccountDAL {

//    List<Account> getAllAccounts();

//    Account getAccountByEmail(String email);

    Account addNewAccount(Account account);
}
