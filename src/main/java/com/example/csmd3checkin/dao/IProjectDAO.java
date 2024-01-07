package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Project;

import java.util.List;

public interface IProjectDAO {
    List<Project> selectProjectIdName();
    void insertProject(Project project);

    List<Project> selectAllProject();
}
