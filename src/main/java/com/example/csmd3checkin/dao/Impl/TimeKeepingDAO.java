package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.ITimeKeepingDAO;
import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeKeepingDAO extends DBConnect implements ITimeKeepingDAO {

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from timekeepings";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public TimeKeepingDAO() {
    }


    @Override
    public void insertTimeKeeping(TimeKeeping timeKeeping) throws SQLException {

    }

    @Override
    public TimeKeeping selectTimeKeepingById(int id) {
        return null;
    }

    @Override
    public List<TimeKeeping> selectAllTimeKeeping() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<TimeKeeping> timeKeepings = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");

                timeKeepings.add(new TimeKeeping(
                        rs.getInt("id"),
                        rs.getTimestamp("day").toLocalDateTime(),
                        rs.getTime("time_checkin").toLocalTime(),
                        rs.getTime("time_checkout").toLocalTime(),
                        (rs.getInt("status")==1),
                        rs.getInt("members_id")

                        ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return timeKeepings;
    }

    @Override
    public boolean deleteTimeKeeping(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTimeKeeping(TimeKeeping timeKeeping) throws SQLException {
        return false;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
