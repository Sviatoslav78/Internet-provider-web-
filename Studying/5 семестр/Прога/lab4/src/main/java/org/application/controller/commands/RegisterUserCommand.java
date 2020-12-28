package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.entity.Subscriber;
import org.application.model.service.RegisterService;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand extends Command {
    private RegisterService registerService;
    private static final Logger logger = Logger.getLogger(RegisterUserCommand.class);

    public RegisterUserCommand() {
        registerService = new RegisterService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String newSubscriberName = request.getParameter("subscriberName");

        if (Validator.isValidSubscriberName(newSubscriberName)) {
            logger.info("admin registered new user with name '" + newSubscriberName + "', " +
                    "sess_id=" + request.getRequestedSessionId());
            Subscriber newSubscriber = registerService.registerUser(newSubscriberName);
            request.setAttribute("registerUserResponse", request.getSession().getAttribute("registerUserSuccess"));
            request.setAttribute("newSubscriber", newSubscriber);
        } else {
            logger.error("admin tried to register new user, but its name '" + newSubscriberName + "' is invalid, " +
                    "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("registerUserResponse", request.getSession().getAttribute("registerUserInvalid"));
        }

        return "forward$/admin/edit-users/register-user";
    }
}
