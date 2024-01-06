package com.example.csmd3checkin.filter;

import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.enumration.ERole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@javax.servlet.annotation.WebFilter("/admin-page/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        var session = ((HttpServletRequest)response).getSession();
//        if(session.getAttribute("account") == null){
//            ((HttpServletResponse)response).sendRedirect("/auths");
//            return;
//        }
//        if(!((Account) session.getAttribute("account")).getRole().equals(ERole.ADMIN)){
//            ((HttpServletResponse)response).sendRedirect("/admin-page?action=confirmLogin");
//            return;
//        }
//        chain.doFilter(request, response);
    }
}
