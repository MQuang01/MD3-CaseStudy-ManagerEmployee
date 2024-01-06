package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Team;

import java.util.List;

public interface ITeamDAO {
    List<Team> selectAllTeam();
    List<Team> selectTeamGroupById();
}
