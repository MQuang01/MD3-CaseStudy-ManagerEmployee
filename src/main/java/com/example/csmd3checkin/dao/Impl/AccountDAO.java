package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IAccountDAO;
import com.example.csmd3checkin.model.Account;

import java.sql.PreparedStatement;

public class AccountDAO extends DBConnect implements IAccountDAO {

    @Override
    public boolean checkLogin(Account account) {

        return false;
    }
}
