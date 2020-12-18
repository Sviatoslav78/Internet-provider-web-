package org.application.controller.commands;

import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class DeleteTariffCommand extends Command {
    private TariffService tariffService;

    public DeleteTariffCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter("tariffName");

        if (Validator.isValidTariffName(tariffName)) {
            if (tariffService.deleteTariff(tariffName)) {
                request.setAttribute("deleteTariffResponse", "Tariff was successfully deleted");
            } else {
                request.setAttribute("deleteTariffResponse", "Tariff wasn't deleted(it doesn't exist)");
            }
            return "forward$/admin/edit-tariffs/delete-tariff";

        } else {
            request.setAttribute("deleteTariffResponse", "Invalid tariff name");
            return "forward$/admin/edit-tariffs/delete-tariff";
        }


    }
}
