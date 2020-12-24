package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.entity.Subscriber;
import org.application.model.service.SubscriberProfileService;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class ShowProfileInfoCommand extends Command {
    private SubscriberProfileService subscriberProfileService;
    private static final Logger logger = Logger.getLogger(ShowProfileInfoCommand.class);


    public ShowProfileInfoCommand() {
        subscriberProfileService = new SubscriberProfileService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("currentUser");
        Subscriber subscriber = subscriberProfileService.getUserInfo(login);

        logger.info("User '" + login + "' opened profile page successfully, sess_id=" + request.getRequestedSessionId());
        request.setAttribute("currentUserInfo", subscriber);
        request.setAttribute("currentUserTariffs", subscriberProfileService.getSubscriberTariffs(login));
        return "forward$/user/show-profile";
    }
}
