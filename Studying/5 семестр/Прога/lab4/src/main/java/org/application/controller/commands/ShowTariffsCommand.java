package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
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
        List<Tariff> tariffList = new ArrayList<>();
        tariffList = tariffService.getTariffsAsc();

        logger.info("available tariffs for client( " + request.getSession().getAttribute("currentUser") +
                ") were loaded successfully, sess_id=" + request.getRequestedSessionId());
        request.setAttribute("availableTariffs", tariffList);

        return "forward$/show-tariffs";
    }

}
