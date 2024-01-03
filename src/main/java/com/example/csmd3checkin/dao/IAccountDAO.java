package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Account;

public interface IAccountDAO {
    Account checkLoginCorrect(Account account);
}
