package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IMemberDAO;
import com.example.csmd3checkin.model.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MemberDAO extends DBConnect implements IMemberDAO {
    private static final String SELECT_MEMBER = "select * from members " +
                                                    "inner join accounts on accounts.id = members.accounts_id " +
                                                        "where ( accounts.id = ? );";

    public MemberDAO() {
    }

    @Override
    public Member selectMemberById(int id) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_MEMBER)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                int idMember = rs.getInt("id");
                String nameMember = rs.getString("name");
                String phoneMember = rs.getString("phone");
                LocalDate date = rs.getDate("dob").toLocalDate();
                String email = rs.getString("email");
                int teamId = rs.getInt("teamId");
                int accountId = rs.getInt("accounts_id");

                return new Member(idMember, nameMember, phoneMember, date, email, teamId, accountId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
