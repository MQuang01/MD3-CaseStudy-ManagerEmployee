package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.ITeamDAO;
import com.example.csmd3checkin.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO extends DBConnect implements ITeamDAO {
    private static final String SELECT_ALL_TEAM="select * from teams";
    private static final String SELECT_ALL_TEAM_GROUP_BY_ID = "select teams.id, teams.`name` from members\n" +
            "inner join teams on teams.id = members.teamId\n" +
            "inner join accounts on accounts.id = members.accounts_id\n" +
            "where accounts.`role` = 'employee'\n" +
            "group by teams.id, teams.`name`";
    @Override
    public List<Team> selectAllTeam() {
        List<Team> teams = new ArrayList<>();
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_TEAM);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                teams.add(new Team(
                        rs.getInt("id"),
                        rs.getString("name")
                ));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return teams;
    }

    @Override
    public List<Team> selectTeamGroupById() {
        List<Team> teams = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_TEAM_GROUP_BY_ID)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                teams.add(new Team(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return teams;
    }

}
