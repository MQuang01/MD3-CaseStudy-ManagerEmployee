package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.ITimeKeepingDAO;
import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeKeepingDAO extends DBConnect implements ITimeKeepingDAO {

    private static final String SELECT_TIMEKEEPING = "select * from time_keepings where (`memberId` = ?) and (`day` like '%' ? '%')";
    private static final String SELECT_ALL_TIMEKEEPING_OF = "select * from time_keepings where (`memberId` = ?)";
    private static final String UPDATE_CHECK_IN_SQL = "UPDATE `time_keepings` SET `time_checkin` = ? , status = ? WHERE (`memberId` = ?) and (`day` like '%' ? '%')";
    private static final String UPDATE_CHECK_OUT_SQL = "UPDATE `time_keepings` SET `time_checkout` = ? WHERE (`memberId` = ?) and (`day` like '%' ? '%')";

    public TimeKeepingDAO() {
    }


    @Override
    public TimeKeeping selectTimeKeeping(Member member, LocalDateTime day) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TIMEKEEPING)) {
            preparedStatement.setInt(1, member.getId());
            Date dayNow = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(2, dayNow);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int idTimeK = rs.getInt("id");
                Timestamp dayDB = rs.getTimestamp("day");
                LocalDate dateTimeK = LocalDate.from(dayDB.toLocalDateTime());
                boolean statusTimeK = rs.getBoolean("status");
                public void insertTimeKeeping (TimeKeeping timeKeeping){

                }
            }
        }
    }

    @Override
    public boolean updateTimeCheckout(Member member) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_CHECK_OUT_SQL)) {
            LocalTime timeCheck = LocalTime.now();
            preparedStatement.setTime(1, Time.valueOf(timeCheck));
            preparedStatement.setInt(2, member.getId());
            Date dayCheck = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(3, dayCheck);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        public boolean updateTimeKeeping (TimeKeeping timeKeeping){
            return false;
        }

        private void printSQLException (SQLException ex){
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

    @Override
    public boolean updateTimeCheckin(Member member) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_CHECK_IN_SQL)) {
            LocalTime timeCheck = LocalTime.now();
            preparedStatement.setTime(1, Time.valueOf(timeCheck));
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, member.getId());
            Date dayCheck = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(4, dayCheck);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTimeKeeping(int id) {
        return false;
    }

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

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = LocalDate.from(rs.getTimestamp("day").toLocalDateTime());
                LocalTime timeCheckin = null;
                if (rs.getTime("time_checkin") != null) {
                    timeCheckin = rs.getTime("time_checkin").toLocalTime();
                }

                LocalTime timeCheckout = null;
                if (rs.getTime("time_checkout") != null) {
                    timeCheckout = rs.getTime("time_checkout").toLocalTime();
                }
                boolean status = rs.getBoolean("status");

                listTime.add(new TimeKeeping(id, date, timeCheckin, timeCheckout, status, member));

//                int id = rs.getInt("id");
//                String name = rs.getString("name");

                timeKeepings.add(new TimeKeeping(
                        rs.getInt("id"),
                        rs.getTimestamp("day").toLocalDateTime(),
                        rs.getTime("time_checkin").toLocalTime(),
                        rs.getTime("time_checkout").toLocalTime(),
                        (rs.getInt("status") == 1),
                        rs.getInt("members_id")

                ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTime;
    }

    @Override
    public List<TimeKeeping> selectTimeKeepingOf(Member member) {
        List<TimeKeeping> listTime = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_TIMEKEEPING_OF)) {
            preparedStatement.setInt(1, member.getId());


        }
    }
