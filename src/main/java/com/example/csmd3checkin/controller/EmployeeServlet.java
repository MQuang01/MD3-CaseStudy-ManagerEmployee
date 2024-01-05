package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.Impl.TimeKeepingDAO;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name= "employeeServlets", value = "/employee-page")
public class EmployeeServlet extends HttpServlet {
    private TimeKeepingDAO timeKeepingDAO;
    public void init(){
        timeKeepingDAO = new TimeKeepingDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showEmployeePage(req, resp);
    }

    private void showEmployeePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");
        TimeKeeping timeKeeping = timeKeepingDAO.selectTimeKeeping(member, LocalDateTime.now());

        List<TimeKeeping> timeKeepingList = timeKeepingDAO.selectTimeKeepingOf(member);

        String wordBoxCheck = timeKeeping.isStatus() ? "Check out" : "Check in";
        session.setAttribute("word", wordBoxCheck);
        req.setAttribute("listCheckin", timeKeepingList);
        req.setAttribute("member", member);

        req.getRequestDispatcher("jsp/pagesIndex/indexEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
