package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.ManagerCommands;
import org.application.controller.Validator;
import org.application.model.entity.ServiceType;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class AddTariffCommand extends Command {
    private TariffService tariffService;

    public AddTariffCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int serviceType = Integer.parseInt(request.getParameter("serviceType"));
        String tariffName = request.getParameter("tariffName");
        int tariffPrice = 0;

        try {
            tariffPrice = Integer.parseInt(request.getParameter("tariffPrice"));
        } catch (NumberFormatException e) {
            request.setAttribute("addTariffResponse", "Invalid price");

            return "forward$/admin/edit-tariffs/add-tariff";
        }

        if (Validator.isValidTariffName(tariffName) && tariffPrice > 0) {
            if (tariffService.addTariff(new Tariff(ServiceType.values()[serviceType], tariffName, tariffPrice))) {
                request.setAttribute("addTariffResponse", "Tariff was added successfully");
            } else {
                request.setAttribute("addTariffResponse", "Tariff already exists");
            }
        } else {
            request.setAttribute("addTariffResponse", "Invalid tariff name or price is negative");
        }
        return "forward$/admin/edit-tariffs/add-tariff";
    }
}
