package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.ITimeKeepingDAO;
import com.example.csmd3checkin.dao.Impl.TimeKeepingDAO;

import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.TimeKeeping;
import com.example.csmd3checkin.model.enumration.ERole;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.example.csmd3checkin.dao.Util.checkLateOnTimeAbsent;

@WebServlet(name="manager-empl", value = "/timekeeping")
public class TimeKeepingServlet extends HttpServlet {
    private TimeKeepingDAO timeKeepingDAO;
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
            case "req-checkin":
                checkInDB(req,resp);
                break;
            case "req-checkout":
                checkOutDB(req, resp);
                break;
            default:
                showCheckinForm(req, resp);
                break;
        }
    }

    private void checkInDB(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");

        if(timeKeepingDAO.updateTimeCheckin(member)){
            if (member.getAccount().getRole().equals(ERole.ADMIN)){
                resp.sendRedirect("/admin-page");
            }else {
                resp.sendRedirect("/employee-page");
            }
        }else {
            req.setAttribute("message", "Check-in failed in database");
            resp.sendRedirect("/timekeeping");
        }
    }
    private void checkOutDB(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");

        if(timeKeepingDAO.updateTimeCheckout(member)){
            if (member.getAccount().getRole().equals(ERole.ADMIN)){
                resp.sendRedirect("/admin-page");
            }else {
                resp.sendRedirect("/employee-page");
            }
        }else {
            req.setAttribute("message", "Check-out failed in database");
            resp.sendRedirect("/timekeeping");
        }
    }
    private void showCheckinForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");

        req.setAttribute("memberJs", new ObjectMapper().writeValueAsString(member.getFullName()));

        req.getRequestDispatcher("/jsp/checkin/checkin.jsp").forward(req, resp);
    }



    private void showTimeKeeping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp").forward(request, response);

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
