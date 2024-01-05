package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;

import java.util.List;

public interface IMemberDAO {
    Member selectMemberById(int id, Account account);
    List<Member> selectAllMember();
    void insertMember(Member member);

    boolean deleteMember(int id);
    boolean updateMember(Member member);
}
