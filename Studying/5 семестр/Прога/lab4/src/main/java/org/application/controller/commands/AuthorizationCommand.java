package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.entity.Subscriber;
import org.application.model.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand extends Command {
    private AuthorizationService authorizationService;
    private static final Logger logger = Logger.getLogger(AuthorizationCommand.class);

    public AuthorizationCommand() {
        authorizationService = new AuthorizationService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (!Validator.isValidLogin(login) || !Validator.isValidPassword(password)) {
            logger.error("User tried to log in, login '" + login + "' or password '" + password + "' is invalid, " +
                    "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("authError", request.getSession().getAttribute("invalidAuthLabel"));
            return "forward$/login";
        } else if (login.equals("admin123") && password.equals("admin123")) {
            logger.info("admin logged in successfully, sess_id=" + request.getRequestedSessionId());
            request.getSession().setAttribute("currentUser", "admin");
            return "/admin/main-menu";
        }

        Subscriber subscriber = authorizationService.isValidAuth(login, password);
        if (subscriber.getLogin().equals("EMPTY")) {
            logger.error("User tried to log in, invalid login/password, sess_id=" + request.getRequestedSessionId());
            request.setAttribute("authError", request.getSession().getAttribute("invalidAuthLabel"));
            return "forward$/login";
        } else {
            logger.info("User '" + login + "' logged in successfully, sess_id=" + request.getRequestedSessionId());
            request.getSession().setAttribute("currentUser", subscriber.getLogin());
            return "/user/main-menu";
        }
    }
}
