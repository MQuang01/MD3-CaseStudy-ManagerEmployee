package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ITimeKeepingDAO {

    TimeKeeping selectTimeKeeping(Member member, LocalDateTime day);

    List<TimeKeeping> selectTimeKeepingOf(Member member);
    List<TimeKeeping> selectAllTimeKeeping();


    boolean updateTimeCheckin(Member member);

    boolean updateTimeCheckout(Member member);

    List<TimeKeeping> selectAllTimeKeepingPage(int page, int litmit);
    int selectTotalPage(int limit);
}
