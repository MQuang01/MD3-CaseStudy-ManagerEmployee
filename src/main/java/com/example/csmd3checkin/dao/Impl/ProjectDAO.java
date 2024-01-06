package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IProjectDAO;
import com.example.csmd3checkin.model.Project;
import com.example.csmd3checkin.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends DBConnect implements IProjectDAO {
    private static final String SELECT_PROJECT_idname="select id,name from projects";


    @Override
    public List<Project> selectProjectIdName() {
        List<Project> project=new ArrayList<>();
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_PROJECT_idname);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                project.add(new Project(
                        rs.getInt("id"),
                        rs.getString("name")
                ));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return project;
    }
}
