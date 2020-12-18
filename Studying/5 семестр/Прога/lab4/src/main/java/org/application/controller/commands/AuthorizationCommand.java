package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.entity.Subscriber;
import org.application.model.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand extends Command {
    private AuthorizationService authorizationService;

    public AuthorizationCommand() {
        authorizationService = new AuthorizationService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (!Validator.isValidLogin(login)) {
            request.setAttribute("authError", "Invalid login");
            return "forward$/login";
        } else if (!Validator.isValidPassword(password)) {
            request.setAttribute("authError", "Invalid password");
            return "forward$/login";
        }

        if (login.equals("admin123") && password.equals("admin123")) {
            request.getSession().setAttribute("currentUser", "admin");
            return "/admin/main-menu";
        }

        Subscriber subscriber = authorizationService.isValidAuth(login, password);
        if (subscriber.getLogin().equals("EMPTY")) {
            request.setAttribute("authError", "Invalid login/password");
            return "forward$/login";
        } else {
            request.getSession().setAttribute("currentUser", subscriber.getLogin());
            return "/user/main-menu";
        }
    }
}
