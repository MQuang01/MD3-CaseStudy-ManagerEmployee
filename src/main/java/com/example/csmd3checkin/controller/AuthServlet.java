package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.Impl.AccountDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.enumration.ERole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "auths", value = "/auths")
public class AuthServlet extends HttpServlet {
    private AccountDAO accountDAO;

    public void init(){
        accountDAO = new AccountDAO();
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
        if(account != null){
            if(account.getRole().equals(ERole.ADMIN)){
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