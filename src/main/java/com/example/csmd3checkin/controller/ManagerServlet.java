package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.enumration.ERole;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="managerServlets", value = "/manager-page")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null){
            action = "";
        }
        switch (action){
            case "checkin":
                showCheckinForm(req,resp);
                break;
            default:
                showLoginForm(req,resp);
                break;
        }
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session != null){
            session.removeAttribute("account");
        }

        req.getRequestDispatcher("jsp/login/login.jsp").forward(req,resp);
    }

    private void showCheckinForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("account");

        String href = member.getAccount().getRole().equals(ERole.ADMIN) ? "/admin-page" : "/employee-page";
        req.setAttribute("memberJsp", member);

        req.setAttribute("memberJs", new ObjectMapper().writeValueAsString(member.getFullName()));
        req.setAttribute("linkBack", href);

        req.getRequestDispatcher("jsp/checkin/checkin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
