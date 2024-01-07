package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.Impl.MemberDAO;
import com.example.csmd3checkin.dao.Impl.ProjectDAO;
import com.example.csmd3checkin.dao.Impl.TeamDAO;
import com.example.csmd3checkin.dao.Impl.TimeKeepingDAO;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.Project;
import com.example.csmd3checkin.model.Team;
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
    private MemberDAO memberDAO;
    private TeamDAO teamDAO;
    private ProjectDAO projectDAO;
    public void init(){
        timeKeepingDAO = new TimeKeepingDAO();
        teamDAO = new TeamDAO();
        projectDAO=new ProjectDAO();
        memberDAO = new MemberDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "show-profile":
                showProfile(req,resp);
                break;
            case "show-project":
                showProject(req, resp);
                break;
            default:
                showEmployeePage(req, resp);
                break;
        }

    }

    private void showProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        List<Project> projects = projectDAO.selectMyProject(member);
        req.setAttribute("listProject", projects);
        List<Member> teammates = memberDAO.selectTeamMates(member);
        req.setAttribute("listTeammate", teammates);

        req.getRequestDispatcher("/jsp/menuEmployee/show-project.jsp").forward(req, resp);
    }

    private void showEmployeePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        TimeKeeping timeKeeping = timeKeepingDAO.selectTimeKeeping(member, LocalDateTime.now());

        List<TimeKeeping> timeKeepingList = timeKeepingDAO.selectTimeKeepingOf(member);

        String wordBoxCheck = timeKeeping.isStatus() ? "Check out" : "Check in";
        session.setAttribute("word", wordBoxCheck);
        req.setAttribute("listCheckin", timeKeepingList);
        req.setAttribute("member", member);

        req.getRequestDispatcher("jsp/pagesIndex/indexEmployee.jsp").forward(req, resp);
    }
    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        List<Team> listTeam = teamDAO.selectAllTeam();
        req.setAttribute("listTeam", listTeam);
        List<Project> projectIdName = projectDAO.selectProjectIdName();
        req.setAttribute("projectIdName", projectIdName);


        TimeKeeping timeKeeping = timeKeepingDAO.selectTimeKeeping(member, LocalDateTime.now());

        String wordBoxCheck = timeKeeping.isStatus() ? "Check out" : "Check in";
        session.setAttribute("word", wordBoxCheck);

        req.setAttribute("member", member);


        req.getRequestDispatcher("jsp/menuEmployee/show-profile.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
