package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.entity.Subscriber;
import org.application.model.service.SubscriberProfileService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowUsersCommand extends Command {
    private SubscriberProfileService subscriberProfileService;
    private static final Logger logger = Logger.getLogger(ShowUsersCommand.class);

    public ShowUsersCommand() {
        subscriberProfileService = new SubscriberProfileService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Subscriber> usersList = new ArrayList<>();

        usersList = subscriberProfileService.getAllUsersAsc();
        request.setAttribute("availableUsers", usersList);
        logger.info("registered users for admin were loaded successfully, sess_id=" + request.getRequestedSessionId());

        return "forward$/admin/edit-users/show-users";
    }
}
