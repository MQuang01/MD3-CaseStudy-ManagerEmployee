package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IProjectDAO;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.Project;
import com.example.csmd3checkin.model.Team;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.csmd3checkin.dao.Impl.MemberDAO.SELECT_TEAM_BY_ID;


public class ProjectDAO extends DBConnect implements IProjectDAO {
    private static final String SELECT_PROJECT_idname="select id,name,teamId from projects";
    private static final String SELECT_MY_PROJECT = "select p.id, p.`name`, p.deadline, p.`status`, p.teamId from projects p " +
            "inner join teams on teams.id = p.teamId " +
            "inner join members on members.teamId = teams.id " +
            "where (members.id = ?)";
//    private static final String SELECT_PROJECT_idname="select id,name from projects";
    private static final String SELECT_ALL_PROJECT = "select * from projects";
    private static final String INSERT_PROJECT = "INSERT INTO `manager_employees`.`projects` (`name`, `deadline`, `teamId`) VALUES (? , ? , ?);";


    @Override
    public List<Project> selectProjectIdName() {
        List<Project> project = new ArrayList<>();
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_PROJECT_idname);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                project.add(new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("teamId")
                ));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public void insertProject(Project project) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_PROJECT)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, Date.valueOf(project.getDeadLine()));
            preparedStatement.setInt(3, project.getTeamId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> selectAllProject() {
        List<Project> projects= new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_PROJECT)) {
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate deadline = rs.getDate("deadline").toLocalDate();
                boolean status = rs.getBoolean("status");

                projects.add(new Project(id, name, deadline, status));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    @Override
    public List<Project> selectMyProject(Member member) {
        List<Project> myProjects = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_MY_PROJECT)) {
            preparedStatement.setInt(1, member.getId());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate deadline = rs.getDate("deadline").toLocalDate();
                boolean status = rs.getBoolean("status");
                int teamId = rs.getInt("teamId");
                Team team = selectTeamById(teamId);

                myProjects.add(new Project(id , name, deadline, status, team));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return myProjects;
    }

    private Team selectTeamById(int teamId) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TEAM_BY_ID)) {
            preparedStatement.setInt(1, teamId);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Team(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
