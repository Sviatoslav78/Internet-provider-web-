package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class EditTariffCommand extends Command {
    private TariffService tariffService;
    private static final Logger logger = Logger.getLogger(EditTariffCommand.class);

    public EditTariffCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter("tariffName");
        int newTariffPrice;


        if (Validator.isValidSum(request.getParameter("tariffPrice")) && tariffName != null) {
            newTariffPrice = Integer.parseInt(request.getParameter("tariffPrice"));
            tariffService.changeTariffPrice(tariffName, newTariffPrice);

            logger.info("admin changed tariff '" + tariffName + "' price to '" + newTariffPrice + "', " +
                    "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("editTariffResponse", request.getSession().getAttribute("editTariffSuccess"));
        } else {
            logger.error("admin tried to change tariff '" + tariffName + "', price '" + request.getParameter("tariffPrice")
                    + "' is invalid or no tariffs available, sess_id=" + request.getRequestedSessionId());
            request.setAttribute("editTariffResponse", request.getSession().getAttribute("editTariffInvalid"));
        }
        request.setAttribute("currentTariffs", tariffService.getTariffsAsc());
        return "forward$/admin/edit-tariffs/edit-tariff";
    }
}
