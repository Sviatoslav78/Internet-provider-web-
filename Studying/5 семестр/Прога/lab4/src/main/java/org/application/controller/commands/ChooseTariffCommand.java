package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.service.SubscriberTariffService;

import javax.servlet.http.HttpServletRequest;

public class ChooseTariffCommand extends Command {
    private SubscriberTariffService subscriberTariffService;

    public ChooseTariffCommand() {
        subscriberTariffService = new SubscriberTariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter("tariffName");
        String userLogin = (String) request.getSession().getAttribute("currentUser");

        if (Validator.isValidTariffName(tariffName)) {
            switch (subscriberTariffService.chooseTariff(tariffName, userLogin)) {
                case "noTariff":
                    request.setAttribute("chooseTariffResponse", "No such tariff found");
                    break;
                case "inactive":
                    request.setAttribute("chooseTariffResponse", "Sorry, your account is blocked(not enough money)");
                    break;
                case "ok":
                    request.setAttribute("chooseTariffResponse", "Tariff " + tariffName + " was successfully chosen");
                    break;
                case "noMoney":
                    request.setAttribute("chooseTariffResponse", "Tariff " + tariffName +
                            " was chosen, but your account is blocked now due to lack of funds");
            }
        } else {
            request.setAttribute("chooseTariffResponse", "Invalid tariff name");
        }
        return "forward$/user/choose-tariff";
    }
}
