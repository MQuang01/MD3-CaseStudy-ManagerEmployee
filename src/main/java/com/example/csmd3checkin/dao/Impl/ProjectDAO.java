package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IProjectDAO;
import com.example.csmd3checkin.model.Project;
import com.example.csmd3checkin.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends DBConnect implements IProjectDAO {
    private static final String SELECT_PROJECT_idname="select id,name,teamId from projects";
    private static final String SELECT_ALL_PROJECT = "select * from projects";

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
}
