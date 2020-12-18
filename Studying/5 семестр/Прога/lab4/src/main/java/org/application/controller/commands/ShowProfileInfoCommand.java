package org.application.controller.commands;

import org.application.controller.Command;
import org.application.model.entity.Subscriber;
import org.application.model.service.SubscriberProfileService;

import javax.servlet.http.HttpServletRequest;

public class ShowProfileInfoCommand extends Command {
    private SubscriberProfileService subscriberProfileService;

    public ShowProfileInfoCommand() {
        subscriberProfileService = new SubscriberProfileService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("currentUser");
        Subscriber subscriber = subscriberProfileService.getUserInfo(login);

        request.setAttribute("currentUserInfo", subscriber);
        return "forward$/user/show-profile";
    }
}
