package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ITimeKeepingDAO {
    public void insertTimeKeeping(TimeKeeping timeKeeping);

    TimeKeeping selectTimeKeeping(Member member, LocalDateTime day);

    List<TimeKeeping> selectTimeKeepingOf(Member member);


    boolean updateTimeKeeping(TimeKeeping timeKeeping);

    boolean deleteTimeKeeping(int id);

    boolean updateTimeCheckin(Member member);

    boolean updateTimeCheckout(Member member);
}
