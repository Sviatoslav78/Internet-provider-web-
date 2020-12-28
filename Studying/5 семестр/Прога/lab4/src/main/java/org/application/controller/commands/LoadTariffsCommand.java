package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.List;

public class LoadTariffsCommand extends Command {
    private TariffService tariffService;
    private static final Logger logger = Logger.getLogger(LoadTariffsCommand.class);


    public LoadTariffsCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentUser = (String) request.getSession().getAttribute("currentUser");
        String urlToForward = request.getParameter("urlToForward");
        NumberFormat currencyFormat = (NumberFormat) request.getSession().getAttribute("currencyFormat");

        if (currentUser == null) {
            return "forward$/login";
        } else if (currentUser.equals("admin") && urlToForward.split("/")[1].equals("user")) {
            return "forward$/login";
        } else if (!currentUser.equals("admin") && urlToForward.split("/")[1].equals("admin")) {
            return "forward$/login";
        }

        logger.info("available tariffs were loaded on page '" + urlToForward + "', " +
                "sess_id=" + request.getRequestedSessionId());

        List<Tariff> allTariffs = tariffService.getTariffsAsc();
        for (Tariff tariff : allTariffs) {
            tariff.setFormattedPrice(currencyFormat.format(tariff.getPrice()));
        }

        request.setAttribute("currentTariffs", allTariffs);
        return "forward$" + urlToForward;

    }
}
