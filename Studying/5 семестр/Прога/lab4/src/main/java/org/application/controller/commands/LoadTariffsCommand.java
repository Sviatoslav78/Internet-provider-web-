package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

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

        if (currentUser == null) {
            return "forward$/login";
        } else if (currentUser.equals("admin") && urlToForward.split("/")[1].equals("user")) {
            return "forward$/login";
        } else if (!currentUser.equals("admin") && urlToForward.split("/")[1].equals("admin")) {
            return "forward$/login";
        }

        logger.info("available tariffs were loaded on page '" + urlToForward + "', " +
                "sess_id=" + request.getRequestedSessionId());

        request.setAttribute("currentTariffs", tariffService.getTariffsAsc());
        return "forward$" + urlToForward;

    }
}
