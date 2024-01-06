package com.example.csmd3checkin.filter;

import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.enumration.ERole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter("/employee-page/*")
public class EmployeeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        if(session.getAttribute("account") == null){
            ((HttpServletResponse)response).sendRedirect("/auths");
            return;
        }
        if(!((Account) session.getAttribute("account")).getRole().equals(ERole.EMPLOYEE)){
            ((HttpServletResponse)response).sendRedirect("/auths?action=confirmLogin");
            return;
        }
        chain.doFilter(request, response);
    }
}
