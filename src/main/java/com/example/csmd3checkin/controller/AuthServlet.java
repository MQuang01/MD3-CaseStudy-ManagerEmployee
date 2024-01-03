package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.Impl.AccountDAO;
import com.example.csmd3checkin.dao.Impl.MemberDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.enumration.ERole;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "auths", value = "/auths")
public class AuthServlet extends HttpServlet {
    private AccountDAO accountDAO;
    private MemberDAO memberDAO;
    public void init(){
        accountDAO = new AccountDAO();
        memberDAO = new MemberDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLogin(req, resp);
    }

    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Account account = accountDAO.checkLoginCorrect(new Account(req.getParameter("username"), req.getParameter("psw")));
        HttpSession session= req.getSession();

        if(account != null){
            if(account.getRole().equals(ERole.ADMIN)){

                session.setAttribute(account.getUsername(), memberDAO.selectMemberById(account.getId()).getFullName());

                resp.sendRedirect("jsp/pagesIndex/indexAdmin.jsp");
            }
            if (account.getRole().equals(ERole.EMPLOYEE)){
                resp.sendRedirect("jsp/pagesIndex/indexEmployee.jsp");
            }
        }else {
            req.setAttribute("message", "Login Failed!");
            req.getRequestDispatcher("jsp/login/login.jsp").forward(req, resp);
        }
    }
}