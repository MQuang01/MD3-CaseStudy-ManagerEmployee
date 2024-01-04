package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ITimeKeepingDAO {

    public TimeKeeping selectTimeKeeping(Member member, LocalDateTime day);

    public List<TimeKeeping> selectAllTimeKeeping();

    public boolean deleteTimeKeeping(int id) throws SQLException;

    public boolean updateTimeCheckin(Member member);
}
