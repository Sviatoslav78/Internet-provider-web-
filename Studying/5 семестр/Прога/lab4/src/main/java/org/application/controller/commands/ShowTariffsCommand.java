package org.application.controller.commands;

import org.application.controller.Command;
import org.application.model.entity.Tariff;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowTariffsCommand extends Command {
    private TariffService tariffService;

    public ShowTariffsCommand() {
        tariffService = new TariffService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Tariff> tariffList = new ArrayList<>();
        tariffList = tariffService.getTariffsAsc();

        request.setAttribute("availableTariffs", tariffList);

        return "forward$/show-tariffs";
    }

}
