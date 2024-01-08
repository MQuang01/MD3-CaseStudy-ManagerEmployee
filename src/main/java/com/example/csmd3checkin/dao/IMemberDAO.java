package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.Project;
import com.example.csmd3checkin.model.enumration.ERole;

import java.util.List;

public interface IMemberDAO {
    Member selectMemberById(int id, Account account);
    List<Member> selectAllMember();
    void insertMember(Member member);
//    List<Project> selectProjectOfTeam(int teamId);
    void deleteMember(int id);
    boolean updateMember(Member member);
    List<Member> selectAllMemberType(ERole role);

    List<Member> selectTeamMates(Member member);
    Member selectInfoMemberById(int id, Account account);

}
