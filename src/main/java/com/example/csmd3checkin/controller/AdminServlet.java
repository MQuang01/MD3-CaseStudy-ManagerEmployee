package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.Impl.*;
import com.example.csmd3checkin.model.*;
import com.example.csmd3checkin.model.enumration.ERole;

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
import java.util.Locale;

import static com.example.csmd3checkin.dao.Util.checkLateOnTimeAbsent;

@WebServlet(name = "adminServlets", value = "/admin-page")
public class AdminServlet extends HttpServlet {
    private MemberDAO memberDAO;
    private AccountDAO accountDAO;
    private TimeKeepingDAO timeKeepingDAO;
    private TeamDAO teamDAO;
    private ProjectDAO projectDAO;

    public void init() {
        memberDAO = new MemberDAO();
        accountDAO = new AccountDAO();
        timeKeepingDAO = new TimeKeepingDAO();
        teamDAO = new TeamDAO();
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "manager-menu":
                showCreatProjectForm(req, resp);
                break;
            case "create":
                showAddAccountForm(req, resp);
                break;
            case "add-member":
                showAddMemberForm(req, resp);
                break;
            case "show-profile":
                showProfile(req, resp);
                break;
            case "show-myCheck":
                showCheck(req, resp);
                break;
            case "show-allCheck":
                showCheckAll(req, resp);
                break;
            case "delete-member":
                deleteMember(req, resp);
                break;
            default:
                showAdminPage(req, resp);
                break;
        }

    }

    private void showCreatProjectForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Team> teams = teamDAO.selectAllTeam();
        List<Team> teamOfEmpl = teamDAO.selectTeamGroupById();
        List<Member> eListDefault = memberDAO.selectAllMemberType(ERole.EMPLOYEE);


        req.setAttribute("listTeam", teams);
        req.setAttribute("listTeamOption", teamOfEmpl);
        req.setAttribute("listEmployee", eListDefault);

        req.getRequestDispatcher("jsp/menuAdmin/create-project.jsp").forward(req, resp);
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


        req.getRequestDispatcher("jsp/menuAdmin/show-profile.jsp").forward(req, resp);


    }

    private void showCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        TimeKeeping timeKeeping = timeKeepingDAO.selectTimeKeeping(member, LocalDateTime.now());

        List<TimeKeeping> timeKeepingList = timeKeepingDAO.selectTimeKeepingOf(member);

        String wordBoxCheck = timeKeeping.isStatus() ? "Check out" : "Check in";
        session.setAttribute("word", wordBoxCheck);
        req.setAttribute("listCheckin", timeKeepingList);
        req.setAttribute("member", member);

        req.getRequestDispatcher("jsp/menuAdmin/show-check.jsp").forward(req, resp);

    }

    private void showCheckAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Member> listMember = memberDAO.selectAllMember();
        req.setAttribute("listMember", listMember);

        List<TimeKeeping> listTimeKeeping = timeKeepingDAO.selectAllTimeKeeping();
        req.setAttribute("listCheckin", listTimeKeeping);

        req.getRequestDispatcher("jsp/menuAdmin/show-check-all.jsp").forward(req, resp);

    }

    private void showAddAccountForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


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

    private void showAdminPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        TimeKeeping timeKeeping = timeKeepingDAO.selectTimeKeeping(member, LocalDateTime.now());

        String wordBoxCheck = timeKeeping.isStatus() ? "Check out" : "Check in";
        session.setAttribute("word", wordBoxCheck);

        req.setAttribute("member", member);

        List<TimeKeeping> listTimeKeeping = timeKeepingDAO.selectAllTimeKeeping();
        int[] arr=checkLateOnTimeAbsent(listTimeKeeping);
        req.setAttribute("listTimeKeeping1", arr[0]);
        req.setAttribute("listTimeKeeping2", arr[1]);
        req.setAttribute("listTimeKeeping3", arr[2]);


        //show member
        List<Member> listMemberTeam = memberDAO.selectMemberTeam();
        req.setAttribute("listMemberTeam", listMemberTeam);


//        List<Team> listTeam = teamDAO.selectTeamProject();
//        req.setAttribute("listTeam", listTeam);



        req.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp").forward(req, resp);


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
                case "insert-project":
                    insertProject(req, resp);
                    break;
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

    private void insertProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("projectName");
        LocalDate deadline = LocalDate.parse(req.getParameter("deadline"));

        if (deadline.isBefore(LocalDate.now())){
            HttpSession messageError = req.getSession();
            messageError.setAttribute("errorMess", "The deadline cannot be in the past.");
            resp.sendRedirect("/admin-page?act=manager-menu");
            return;
        }

        int teamId = Integer.parseInt(req.getParameter("teamId"));


        projectDAO.insertProject(new Project(name, deadline, teamId));
        resp.sendRedirect("/admin-page");
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
