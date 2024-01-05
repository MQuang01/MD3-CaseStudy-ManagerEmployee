package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.SQLException;
import java.util.List;

public interface ITimeKeepingDAO {
    public void insertTimeKeeping(TimeKeeping timeKeeping) ;

    public TimeKeeping selectTimeKeepingById(int id);

    public List<TimeKeeping> selectAllTimeKeeping();

    public boolean deleteTimeKeeping(int id);

    public boolean updateTimeKeeping(TimeKeeping timeKeeping);
}
