package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.ITeamDAO;
import com.example.csmd3checkin.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO extends DBConnect implements ITeamDAO {
    private static final String SELECT_ALL_TEAM="select id,name from teams";
    private static final String SELECT_TEAM_project="select * from teams";
    @Override
    public List<Team> selectAllTeam() {
        List<Team> team=new ArrayList<>();
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_TEAM);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                team.add(new Team(
                        rs.getInt("id"),
                        rs.getString("name")
                ));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    @Override
    public List<Team> selectTeamProject() {
        List<Team> team=new ArrayList<>();
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_TEAM_project);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                team.add(new Team(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("project_id")
                ));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return team;
    }
}
