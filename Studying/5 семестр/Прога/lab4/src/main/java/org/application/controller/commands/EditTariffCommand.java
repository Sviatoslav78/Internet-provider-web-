package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.entity.ServiceType;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class EditTariffCommand extends Command {
    private TariffService tariffService;

    public EditTariffCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter("tariffName");
        int newTariffPrice = 0;

        try {
            newTariffPrice = Integer.parseInt(request.getParameter("tariffPrice"));
        } catch (NumberFormatException e) {
            request.setAttribute("editTariffResponse", "Invalid price");

            return "forward$/admin/edit-tariffs/edit-tariff";
        }

        if (Validator.isValidTariffName(tariffName) && newTariffPrice > 0) {
            if (tariffService.changeTariffPrice(tariffName, newTariffPrice)) {
                request.setAttribute("editTariffResponse", "Price of the tariff was successfully updated");
            } else {
                request.setAttribute("editTariffResponse", "Price wasn't updated(no such tariff found)");
            }
        } else {
            request.setAttribute("editTariffResponse", "Invalid tariff name or price is negative");
        }
        return "forward$/admin/edit-tariffs/edit-tariff";
    }
}
