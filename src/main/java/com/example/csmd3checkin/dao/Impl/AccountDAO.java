package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IAccountDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.enumration.ERole;

import java.sql.*;

public class AccountDAO extends DBConnect implements IAccountDAO {
    private static final String SELECT_ACCOUNT = "SELECT * FROM accounts WHERE username = ? AND password = ?";
    private static final String INSERT_ACCOUNT = "INSERT INTO accounts (username,password,role) VALUES (?,?,?);";

    private static final String TAKE_NEW_ACCOUNT_ID="SELECT id FROM accounts ORDER BY id DESC LIMIT 1";
    private static final String FIND_ID_ACCOUNT="";


    @Override
    public Account checkLoginCorrect(Account account) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ACCOUNT)){
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String psw = rs.getString("password");
                String nameRole = rs.getString("role");

                ERole role = ERole.findByName(nameRole);

                return new Account(id, username, psw, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void insertAccount(Account account) {
        try (Connection connection=getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(INSERT_ACCOUNT)){

            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3, String.valueOf(account.getRole()));


            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkNewAccountId() {
        System.out.println(TAKE_NEW_ACCOUNT_ID);
        try (Connection connection=getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(TAKE_NEW_ACCOUNT_ID)){

            System.out.println(preparedStatement);
            ResultSet rs= preparedStatement.executeQuery();
            int id=0;
            if(rs.next()){
                id=rs.getInt("id");
            }
            return id;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
