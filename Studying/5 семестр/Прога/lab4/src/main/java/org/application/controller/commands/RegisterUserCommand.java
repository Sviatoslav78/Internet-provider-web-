package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.entity.Subscriber;
import org.application.model.service.RegisterService;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand extends Command {
    private RegisterService registerService;

    public RegisterUserCommand() {
        registerService = new RegisterService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String newSubscriberName = request.getParameter("subscriberName");

        if (Validator.isValidSubscriberName(newSubscriberName)) {
            Subscriber newSubscriber = registerService.registerUser(newSubscriberName);
            request.setAttribute("registerUserResponse", "User was successfully registered, login details:");
            request.setAttribute("newSubscriber", newSubscriber);
        } else {
            request.setAttribute("registerUserResponse", "Invalid subscriber name");
        }

        return "forward$/admin/edit-users/register-user";
    }
}
