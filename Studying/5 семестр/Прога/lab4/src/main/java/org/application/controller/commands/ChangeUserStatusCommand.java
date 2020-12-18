package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.service.ChangeUserStatusService;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserStatusCommand extends Command {
    private ChangeUserStatusService changeUserStatusService;

    public ChangeUserStatusCommand() {
        changeUserStatusService = new ChangeUserStatusService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String action = request.getParameter("status");
        String userLogin = request.getParameter("subscriberLogin");
        boolean wasChanged = false;

        if (Validator.isValidLogin(userLogin)) {
            switch (action) {
                case "block":
                    wasChanged = changeUserStatusService.changeUserStatus(userLogin, false);
                    break;
                case "unblock":
                    wasChanged = changeUserStatusService.changeUserStatus(userLogin, true);
            }
            if (wasChanged) {
                request.setAttribute("changeStatusResponse", "User status was updated");
            } else {
                request.setAttribute("changeStatusResponse", "User status wasn't changed(user doesn't exist)");
            }

        } else {
            request.setAttribute("changeStatusResponse", "Invalid user name");
        }
        return "forward$/admin/edit-users/change-status";

    }
}
