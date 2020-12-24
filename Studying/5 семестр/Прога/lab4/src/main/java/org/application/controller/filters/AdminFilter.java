package org.application.controller.filters;

import org.apache.log4j.Logger;
import org.application.controller.commands.DeleteTariffCommand;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String currentUser = (String) session.getAttribute("currentUser");

        if (currentUser != null && session.getAttribute("currentUser").equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.warn("Access denied to admin page for client with session id =" + req.getRequestedSessionId());
            res.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
