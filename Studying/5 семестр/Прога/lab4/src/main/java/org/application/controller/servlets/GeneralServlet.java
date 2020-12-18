package org.application.controller.servlets;

import org.application.controller.ManagerCommands;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class GeneralServlet extends HttpServlet {
    ManagerCommands managerCommands = new ManagerCommands();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");
        if (command == null) {
            checkSession(request, response);
        } else {
            String commandResponse = managerCommands.getCommand(command).execute(request);

            if (commandResponse.split("\\$")[0].equals("forward")) {
                request.getRequestDispatcher(commandResponse.split("\\$")[1]).forward(request, response);
            } else {
                response.sendRedirect(commandResponse);
            }
        }
    }

    private void checkSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUser = (String) request.getSession().getAttribute("currentUser");

        if (currentUser == null) {
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            if (currentUser.equals("admin")) {
                response.sendRedirect("/admin/main-menu");
            } else {
                response.sendRedirect("/user/main-menu");
            }
        }
    }
}
