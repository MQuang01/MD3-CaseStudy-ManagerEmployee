package com.example.csmd3checkin.dao;

import com.example.csmd3checkin.model.TimeKeeping;

import java.time.LocalTime;
import java.util.List;

public class Util {
    public static int[] checkLateOnTimeAbsent(List<TimeKeeping> listTimeKeeping){
        int[] arr={0,0,0}; //late,on time, no check
        for(TimeKeeping list: listTimeKeeping){
            if (!list.isStatus()){
                arr[2]++;
            } else if(list.getTimeCheckin().isBefore(LocalTime.of(8, 0, 0))){
                arr[0]++;
            } else {
                arr[1]++;
            }
        }

        return arr;
    }
}
