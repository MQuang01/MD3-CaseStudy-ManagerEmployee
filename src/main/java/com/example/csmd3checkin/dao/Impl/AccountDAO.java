package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IAccountDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.enumration.ERole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DBConnect implements IAccountDAO {
    private static final String SELECT_ACCOUNT = "SELECT * FROM accounts WHERE username = ? AND password = ?";

    public AccountDAO() {
    }

    @Override
    public Account checkLoginCorrect(Account account) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ACCOUNT)){
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                String username = rs.getString("username");
                String psw = rs.getString("password");
                String nameRole = rs.getString("role");

                ERole role = ERole.findByName(nameRole);

                return new Account(username, psw, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
