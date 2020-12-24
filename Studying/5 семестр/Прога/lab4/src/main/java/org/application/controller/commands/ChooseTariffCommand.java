package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.service.ChooseTariffService;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class ChooseTariffCommand extends Command {
    private ChooseTariffService chooseTariffService;
    private TariffService tariffService;
    private static final Logger logger = Logger.getLogger(ChooseTariffCommand.class);

    public ChooseTariffCommand() {
        chooseTariffService = new ChooseTariffService();
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter("tariffName");
        String userLogin = (String) request.getSession().getAttribute("currentUser");
        request.setAttribute("currentTariffs", tariffService.getTariffsAsc());

        if (tariffName == null) {
            logger.error("User '" + userLogin + "' tried to choose tariff, but there are no available tariffs, " +
                    "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("chooseTariffResponse", "Sorry, there are no available tariffs");
            return "forward$/user/choose-tariff";
        }

        switch (chooseTariffService.chooseTariff(tariffName, userLogin)) {
            case "inactive":
                logger.error("User '" + userLogin + "' tried to choose tariff, but he is blocked, " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("chooseTariffResponse", "Sorry, your account is blocked(not enough money)");
                break;
            case "sameTariff":
                logger.warn("User '" + userLogin + "' tried to choose tariff " + tariffName + "' but it's already chosen, " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("chooseTariffResponse", "Tariff " + tariffName +
                        " is already chosen");
                break;
            case "ok":
                logger.info("User '" + userLogin + "' chose tariff " + tariffName + "' successfully, " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("chooseTariffResponse", "Tariff " + tariffName + " was successfully chosen");
                break;
            case "noMoney":
                logger.warn("User '" + userLogin + "' chose tariff " + tariffName + "' and is blocked now, " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("chooseTariffResponse", "Tariff " + tariffName +
                        " was chosen, but your account is blocked now due to lack of funds");
                break;
        }
        return "forward$/user/choose-tariff";
    }
}
