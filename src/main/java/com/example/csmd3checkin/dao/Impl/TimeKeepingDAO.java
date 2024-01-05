package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.ITimeKeepingDAO;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeKeepingDAO extends DBConnect implements ITimeKeepingDAO {

    private static final String SELECT_TIMEKEEPING = "select * from time_keepings where (`memberId` = ?) and (`day` like '%' ? '%')";
    private static final String SELECT_ALL_TIMEKEEPING = "select * from time_keepings";
    private static final String UPDATE_CHECK_IN_SQL = "UPDATE `time_keepings` SET `time_checkin` = ? , status = ? WHERE (`memberId` = ?) and (`day` like '%' ? '%')";
    private static final String UPDATE_CHECK_OUT_SQL = "UPDATE `time_keepings` SET `time_checkout` = ? WHERE (`memberId` = ?) and (`day` like '%' ? '%')";

    //    private static final String UPDATE_CHECK_OUT_SQL =
    public TimeKeepingDAO() {
    }


    @Override
    public TimeKeeping selectTimeKeeping(Member member, LocalDateTime day) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TIMEKEEPING)) {
            preparedStatement.setInt(1, member.getId());
            Date dayNow = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(2, dayNow);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int idTimeK = rs.getInt("id");
                Timestamp dayDB = rs.getTimestamp("day");
                LocalDateTime dateTimeK = dayDB.toLocalDateTime();
                boolean statusTimeK = rs.getBoolean("status");

                return new TimeKeeping(idTimeK, dateTimeK, statusTimeK, member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<TimeKeeping> selectAllTimeKeeping() {
        List<TimeKeeping> timeKeepings = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_TIMEKEEPING)) {

            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
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
        return timeKeepings;
    }

    @Override
    public boolean deleteTimeKeeping(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTimeCheckin(Member member) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_CHECK_IN_SQL)) {
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
    public boolean updateTimeCheckout(Member member) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_CHECK_OUT_SQL)) {
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
