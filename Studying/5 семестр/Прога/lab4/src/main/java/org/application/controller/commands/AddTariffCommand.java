package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.entity.ServiceType;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class AddTariffCommand extends Command {
    private TariffService tariffService;
    private static final Logger logger = Logger.getLogger(AddTariffCommand.class);

    public AddTariffCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int serviceType = Integer.parseInt(request.getParameter("serviceType"));
        String tariffName = request.getParameter("tariffName");
        int tariffPrice;

        if (Validator.isValidTariffName(tariffName) && Validator.isValidSum(request.getParameter("tariffPrice"))) {
            tariffPrice = Integer.parseInt(request.getParameter("tariffPrice"));

            if (tariffService.addTariff(new Tariff(ServiceType.values()[serviceType], tariffName, tariffPrice))) {
                logger.info("admin added tariff '" + tariffName + "', sess_id=" + request.getRequestedSessionId());
                request.setAttribute("addTariffResponse", request.getSession().getAttribute("addTariffSuccess"));
            } else {
                logger.warn("admin tried to add tariff '" + tariffName + "', it already exists, " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("addTariffResponse", request.getSession().getAttribute("addTariffExists"));
            }
        } else {
            logger.error("admin tried to add tariff '" + tariffName + "' with price '" + request.getParameter("tariffPrice")
                    + "', invalid tariff name or price, " + "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("addTariffResponse", request.getSession().getAttribute("addTariffInvalid"));
        }
        return "forward$/admin/edit-tariffs/add-tariff";
    }
}
