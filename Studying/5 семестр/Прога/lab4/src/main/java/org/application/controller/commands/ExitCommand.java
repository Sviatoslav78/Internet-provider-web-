package org.application.controller.commands;

import org.application.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class ExitCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().invalidate();
        return "/login";
    }
}
