package org.application.controller.commands;

import org.apache.log4j.Logger;
import org.application.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class ExitCommand extends Command {
    private static final Logger logger = Logger.getLogger(ExitCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("client '" + request.getSession().getAttribute("currentUser") + "' logged out successfully, " +
                "sess_id=" + request.getRequestedSessionId());
        String language = (String) request.getSession().getAttribute("lang");
        request.getSession().invalidate();
        request.getSession().setAttribute("lang", language);
        return "/?command=changeLanguage";
    }
}
