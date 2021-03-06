package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class DeleteTariffCommand extends Command {
    private TariffService tariffService;
    private static final Logger logger = Logger.getLogger(DeleteTariffCommand.class);

    public DeleteTariffCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter("tariffName");
        if (tariffName != null) {
            tariffService.deleteTariff(tariffName);
            logger.info("admin deleted tariff '" + tariffName + "', sess_id=" + request.getRequestedSessionId());
            request.setAttribute("deleteTariffResponse", request.getSession().getAttribute("deleteTariffSuccess"));

            request.setAttribute("currentTariffs", tariffService.getTariffsAsc());
        } else {
            logger.error("admin tried to delete tariff, but there are no tariffs, " +
                    "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("deleteTariffResponse", request.getSession().getAttribute("deleteTariffNoTariffs"));
        }

        return "forward$/admin/edit-tariffs/delete-tariff";
    }
}
