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
    private static final String SELECT_ALL_TIMEKEEPING_OF = "select * from time_keepings where (`memberId` = ?)";
    private static final String UPDATE_CHECK_IN_SQL = "UPDATE `time_keepings` SET `time_checkin` = ? , status = ? WHERE (`memberId` = ?) and (`day` like '%' ? '%')";
    private static final String UPDATE_CHECK_OUT_SQL = "UPDATE `time_keepings` SET `time_checkout` = ? WHERE (`memberId` = ?) and (`day` like '%' ? '%')";
    private static final String SELECT_ALL_TIMEKEEPING = "select * from time_keepings";
    private static final String SELECT_TIMEKEEPING_PAGE = "select * from time_keepings LIMIT ? OFFSET ?";
    private static final String SELECT_MEMBER_BY_ID = "select * from members where (id = ?)";
    private static final String SELECT_TOTAL_PAGE = "select count(1) from time_keepings";


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

                return new TimeKeeping(idTimeK, dateTimeK, statusTimeK, member);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
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
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TimeKeeping> selectAllTimeKeepingPage(int page, int limit) {
        List<TimeKeeping> listTime = new ArrayList<>();

        int offset = (page - 1) * limit;

        // Step 1: Establishing a Connection
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TIMEKEEPING_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

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

                int memberId = rs.getInt("memberId");
                Member member = selectMemberById(memberId);

                listTime.add(new TimeKeeping(id, date, timeCheckin, timeCheckout, status, member));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTime;
    }

    @Override
    public int selectTotalPage(int limit) {
        double totalPage = 0;
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TOTAL_PAGE)) {
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                 totalPage = rs.getDouble("count(1)");
                 return  (int) Math.ceil(totalPage/limit);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    @Override
    public List<TimeKeeping> selectTimeKeepingOf(Member member) {
        List<TimeKeeping> listTime = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_TIMEKEEPING_OF)) {
            preparedStatement.setInt(1, member.getId());

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
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTime;
    }

    @Override
    public List<TimeKeeping> selectAllTimeKeeping() {
        List<TimeKeeping> listTime = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_TIMEKEEPING)) {

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
                int memberId = rs.getInt("memberId");
                Member member = selectMemberById(memberId);

                listTime.add(new TimeKeeping(id, date, timeCheckin, timeCheckout, status, memberId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTime;
    }

    private Member selectMemberById(int memberId) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_MEMBER_BY_ID)) {
            preparedStatement.setInt(1, memberId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Member(
                      memberId,
                      rs.getString("name")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
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

