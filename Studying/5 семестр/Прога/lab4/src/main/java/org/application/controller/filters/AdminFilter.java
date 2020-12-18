package org.application.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String currentUser = (String) session.getAttribute("currentUser");

        if (currentUser == null) {
            res.sendRedirect("/login");
        } else {
            if (session.getAttribute("currentUser").equals("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                res.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
