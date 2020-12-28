package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.entity.Subscriber;
import org.application.model.entity.Tariff;
import org.application.model.service.SubscriberProfileService;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.List;

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
        NumberFormat currencyFormat = (NumberFormat) request.getSession().getAttribute("currencyFormat");
        subscriber.setFormattedBalance(currencyFormat.format(subscriber.getBalance()));

        logger.info("User '" + login + "' opened profile page successfully, sess_id=" + request.getRequestedSessionId());
        request.setAttribute("currentUserInfo", subscriber);

        List<Tariff> subscriberTariffs = subscriberProfileService.getSubscriberTariffs(login);
        for (Tariff tariff :subscriberTariffs) {
            tariff.setFormattedPrice(currencyFormat.format(tariff.getPrice()));
        }

        request.setAttribute("currentUserTariffs", subscriberTariffs);
        return "forward$/user/show-profile";
    }
}
