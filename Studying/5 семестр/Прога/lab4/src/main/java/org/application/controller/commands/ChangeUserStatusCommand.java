package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.service.ChangeUserStatusService;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserStatusCommand extends Command {
    private ChangeUserStatusService changeUserStatusService;
    private static final Logger logger = Logger.getLogger(ChangeUserStatusCommand.class);

    public ChangeUserStatusCommand() {
        changeUserStatusService = new ChangeUserStatusService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String action = request.getParameter("status");
        action.replace("Розблокований", "unblock");
        action.replace("Заблокований", "block");
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
                logger.info("admin " + action + "ed user '" + userLogin + "', sess_id=" + request.getRequestedSessionId());
                request.setAttribute("changeStatusResponse", request.getSession().getAttribute("updateStatusSuccess"));
            } else {
                logger.error("admin tried to update user '" + userLogin + "' status, but it doesn't exist," +
                        " sess_id=" + request.getRequestedSessionId());
                request.setAttribute("changeStatusResponse", request.getSession().getAttribute("updateStatusNotExist"));
            }
        } else {
            logger.error("admin tried to update user '" + userLogin + "' status, but login is invalid," +
                    " sess_id=" + request.getRequestedSessionId());
            request.setAttribute("changeStatusResponse", request.getSession().getAttribute("updateStatusInvalid"));
        }
        return "forward$/admin/edit-users/change-status";
    }
}
