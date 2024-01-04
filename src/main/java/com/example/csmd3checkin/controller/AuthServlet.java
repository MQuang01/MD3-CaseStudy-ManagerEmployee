package com.example.csmd3checkin.controller;

import com.example.csmd3checkin.dao.ITimeKeepingDAO;
import com.example.csmd3checkin.dao.Impl.AccountDAO;
import com.example.csmd3checkin.dao.Impl.TimeKeepingDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.TimeKeeping;
import com.example.csmd3checkin.model.enumration.ERole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "auths", value = "/auths")
public class AuthServlet extends HttpServlet {
    private AccountDAO accountDAO;
    private ITimeKeepingDAO timeKeepingDAO;
    public void init(){
        accountDAO = new AccountDAO();
        timeKeepingDAO = new TimeKeepingDAO();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showLoginForm(req, resp);
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
            session.removeAttribute("account");
        }

        req.getRequestDispatcher("jsp/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLogin(req, resp);
    }

    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Account account = accountDAO.checkLoginCorrect(new Account(req.getParameter("username"), req.getParameter("psw")));
        if(account != null){
            if(account.getRole().equals(ERole.ADMIN)){
                List<TimeKeeping> listTimeKeeping = timeKeepingDAO.selectAllTimeKeeping();
                int[] checkLateTimeAb= checkLateOnTimeAbsent(listTimeKeeping);
                System.out.println(Arrays.toString(checkLateTimeAb));
                req.setAttribute("dataCheckIn", checkLateTimeAb);
                req.getRequestDispatcher("jsp/pagesIndex/indexAdmin.jsp").forward(req, resp);

                //admin
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

    private int[] checkLateOnTimeAbsent(List<TimeKeeping> listTimeKeeping){
        int[] arr={0,0,0}; //late,on time, no check
        for(TimeKeeping list: listTimeKeeping){
            if (!list.isStatus()){
                arr[2]++;
            } else if(list.getTimeCheckin().isBefore(LocalTime.of(8, 0, 0))){
                arr[0]++;
            } else {
                arr[1]++;
            }
        }

        return arr;
    }
}