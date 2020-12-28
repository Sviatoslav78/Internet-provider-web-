package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowTariffsCommand extends Command {
    private TariffService tariffService;
    private static final Logger logger = Logger.getLogger(ShowTariffsCommand.class);


    public ShowTariffsCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        NumberFormat currencyFormat = (NumberFormat) request.getSession().getAttribute("currencyFormat");

        List<Tariff> tariffList = tariffService.getTariffsAsc();
        for (Tariff tariff : tariffList) {
            tariff.setFormattedPrice(currencyFormat.format(tariff.getPrice()));
        }


        logger.info("available tariffs for client( " + request.getSession().getAttribute("currentUser") +
                ") were loaded successfully, sess_id=" + request.getRequestedSessionId());
        request.setAttribute("availableTariffs", tariffList);

        return "forward$/show-tariffs";
    }

}
