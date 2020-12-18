package org.application.controller.commands;

import org.application.controller.Command;
import org.application.model.service.DepositService;

import javax.servlet.http.HttpServletRequest;

public class DepositCommand extends Command {
    private DepositService depositService;

    public DepositCommand() {
        depositService = new DepositService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int depositSum = Integer.parseInt(request.getParameter("depositSum"));
        String userLogin = (String) request.getSession().getAttribute("currentUser");

        if (depositSum <= 0) {
            request.setAttribute("depositCommandResponse", "Invalid deposit sum");
        } else {
            if (depositService.topUpAccount(depositSum, userLogin)) {
                request.setAttribute("depositCommandResponse", "Transaction finished successfully, your account" +
                        " status is active now");
            } else {
                request.setAttribute("depositCommandResponse", "Transaction finished successfully, but your" +
                        " account is still blocked");
            }
        }
        return "forward$/user/top-up";
    }
}
