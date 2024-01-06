package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Account;

import java.util.List;

public interface IAccountDAO {
    Account checkLoginCorrect(Account account);
    void insertAccount(Account account);
    List<Account> selectAllAccount();
    int checkNewAccountId();
}
