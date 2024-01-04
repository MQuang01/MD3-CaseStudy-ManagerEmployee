package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;

public interface IMemberDAO {
    Member selectMemberById(int id, Account account);
}
