package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;
import org.application.controller.Validator;
import org.application.model.service.DepositService;

import javax.servlet.http.HttpServletRequest;

public class DepositCommand extends Command {
    private DepositService depositService;
    private static final Logger logger = Logger.getLogger(DepositCommand.class);

    public DepositCommand() {
        depositService = new DepositService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int depositSum = 0;
        String userLogin = (String) request.getSession().getAttribute("currentUser");

        if (!Validator.isValidSum(request.getParameter("depositSum"))) {
            logger.error("user '" + userLogin + "' tried to top up account, deposit sum '" + depositSum + "' is invalid, " +
                    "sess_id=" + request.getRequestedSessionId());
            request.setAttribute("depositCommandResponse", "Invalid deposit sum");
        } else {
            depositSum = Integer.parseInt(request.getParameter("depositSum"));

            if (depositService.topUpAccount(depositSum, userLogin)) {
                logger.info("user '" + userLogin + "' topped up account by '" + depositSum + "', " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("depositCommandResponse", "Transaction finished successfully, your account" +
                        " status is active now");
            } else {
                logger.warn("user '" + userLogin + "' topped up account by '" + depositSum + "', but is still blocked, " +
                        "sess_id=" + request.getRequestedSessionId());
                request.setAttribute("depositCommandResponse", "Transaction finished successfully, but your" +
                        " account is still blocked");
            }
        }
        return "forward$/user/top-up";
    }
}
