package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.model.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name= "adminServlets", value = "/admin-page")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if(action == null){
            action = "";
        }

        switch (action){
            case "add-task":
                showAddTaskForm(req, resp);
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
        req.setAttribute("member", member);

        req.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
