package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.ITimeKeepingDAO;
import com.example.csmd3checkin.dao.Impl.TimeKeepingDAO;
import com.example.csmd3checkin.dao.Util;
import com.example.csmd3checkin.model.TimeKeeping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.example.csmd3checkin.dao.Util.checkLateOnTimeAbsent;

@WebServlet(name="manager-empl", value = "/timekeeping")
public class TimeKeepingServlet extends HttpServlet {

    private ITimeKeepingDAO timeKeepingDAO;
    public void init() {
        timeKeepingDAO = new TimeKeepingDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null){
            action = "";
        }
        switch (action){
//            case "checkin":
//                showCheckinForm(req,resp);
//                break;
            default:
                showTimeKeeping(req,resp);
                break;
        }
    }

    private void showTimeKeeping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/login/login.jsp").forward(req,resp);

        List<TimeKeeping> listTimeKeeping = timeKeepingDAO.selectAllTimeKeeping();
        int[] checkLateTimeAb= checkLateOnTimeAbsent(listTimeKeeping);
        System.out.println(Arrays.toString(checkLateTimeAb));
        request.setAttribute("dataCheckIn", checkLateTimeAb);

        request.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
