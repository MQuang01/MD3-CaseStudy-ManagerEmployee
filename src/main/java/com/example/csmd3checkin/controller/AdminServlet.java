package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.Impl.AccountDAO;
import com.example.csmd3checkin.dao.Impl.MemberDAO;
import com.example.csmd3checkin.dao.Impl.TeamDAO;
import com.example.csmd3checkin.dao.Impl.TimeKeepingDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.Team;
import com.example.csmd3checkin.model.enumration.ERole;
import com.example.csmd3checkin.model.TimeKeeping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "adminServlets", value = "/admin-page")
public class AdminServlet extends HttpServlet {
    private MemberDAO memberDAO;
    private AccountDAO accountDAO;
    private TimeKeepingDAO timeKeepingDAO;
    private TeamDAO teamDAO;

    public void init() {
        memberDAO = new MemberDAO();
        accountDAO = new AccountDAO();
        timeKeepingDAO = new TimeKeepingDAO();
        teamDAO = new TeamDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add-task":
                showAddTaskForm(req, resp);
                break;

            case "add-account":
                showAddAccountForm(req, resp);
                break;
            case "add-member":
                showAddMemberForm(req, resp);
                break;

            case "delete-member":
                deleteMember(req, resp);
                break;

            default:
                showAdminPage(req, resp);
                break;
        }

    }

    private void showAddTaskForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");
        req.setAttribute("member", member);

        req.getRequestDispatcher("jsp/menuAdmin/add-task.jsp").forward(req, resp);
    }

    private void showAdminPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");

        TimeKeeping timeKeeping = timeKeepingDAO.selectTimeKeeping(member, LocalDateTime.now());

        String wordBoxCheck = timeKeeping.isStatus() ? "Check out" : "Check in";
        session.setAttribute("word", wordBoxCheck);

        req.setAttribute("member", member);


        req.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp").forward(req, resp);
    }

    private void showAddAccountForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute("account");
//        request.setAttribute("member", member);

        List<ERole> roleList = new ArrayList<>(Arrays.asList(ERole.values()));
        request.setAttribute("roleList", roleList);


        request.getRequestDispatcher("jsp/menuAdmin/add-account.jsp").forward(request, response);
    }

    private void showAddMemberForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        List<Team> teamList = teamDAO.selectAllTeam();
        request.setAttribute("teamList", teamList);

        request.getRequestDispatcher("jsp/menuAdmin/add-member.jsp").forward(request, response);
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        memberDAO.deleteMember(id);

        List<Member> listMember = memberDAO.selectAllMember();
        request.setAttribute("listMember", listMember);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("act");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add-member":
                    insertMember(req, resp);
                    break;
                case "add-account":
                    insertAccount(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertMember(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int newAccountId = accountDAO.checkNewAccountId();

        Member newMember = new Member(
                request.getParameter("name"),
                request.getParameter("phone"),
                LocalDate.parse(request.getParameter("dob"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                request.getParameter("email"),
                Integer.parseInt(request.getParameter("teamId")),
                newAccountId

        );
        memberDAO.insertMember(newMember);


        response.sendRedirect("/admin-page");
    }

    private void insertAccount(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Account newAccount = new Account(
                request.getParameter("username"),
                request.getParameter("password"),
                ERole.findByName(request.getParameter("role")) //admin | employee
        );

        accountDAO.insertAccount(newAccount);


        response.sendRedirect("/admin-page?act=add-member");



    }
}
