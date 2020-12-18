package org.application.controller.commands;

import org.application.controller.Command;
import org.application.model.entity.Subscriber;
import org.application.model.service.SubscriberProfileService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowUsersCommand extends Command {
    private SubscriberProfileService subscriberProfileService;

    public ShowUsersCommand() {
        subscriberProfileService = new SubscriberProfileService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Subscriber> usersList = new ArrayList<>();

        usersList = subscriberProfileService.getAllUsersAsc();
        request.setAttribute("availableUsers", usersList);

        return "forward$/admin/edit-users/show-users";
    }
}
