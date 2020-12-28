package org.application.controller.commands;

import org.application.controller.Command;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request) {
        String language = getLanguage(request);
        Locale locale;

        if (language.equals("ukr")) {
            locale = new Locale("uk", "UA");
        } else {
            locale = Locale.getDefault();
        }

        ResourceBundle resource = ResourceBundle.getBundle("global", locale);
        request.getSession().invalidate();
        request.getSession().setAttribute("lang", language);
        request.getSession().setAttribute("currencyFormat", NumberFormat.getCurrencyInstance(locale));

        request.getSession().setAttribute("backLabel", resource.getString("backLabel"));
        request.getSession().setAttribute("exitLabel", resource.getString("exitLabel"));

        request.getSession().setAttribute("signInLabel", resource.getString("signInLabel"));
        request.getSession().setAttribute("changeLanguageButton", resource.getString("changeLanguageButton"));
        request.getSession().setAttribute("loginLabel", resource.getString("loginLabel"));
        request.getSession().setAttribute("passwordLabel", resource.getString("passwordLabel"));
        request.getSession().setAttribute("signInButton", resource.getString("signInButton"));

        request.getSession().setAttribute("invalidAuthLabel", resource.getString("invalidAuthLabel"));

        request.getSession().setAttribute("editTariffsMenuItem", resource.getString("editTariffsMenuItem"));
        request.getSession().setAttribute("editSubscribersMenuItem", resource.getString("editSubscribersMenuItem"));

        request.getSession().setAttribute("addTariffMenuItem", resource.getString("addTariffMenuItem"));
        request.getSession().setAttribute("deleteTariffMenuItem", resource.getString("deleteTariffMenuItem"));
        request.getSession().setAttribute("editTariffMenuItem", resource.getString("editTariffMenuItem"));
        request.getSession().setAttribute("showTariffsMenuItem", resource.getString("showTariffsMenuItem"));

        request.getSession().setAttribute("registerMenuItem", resource.getString("registerMenuItem"));
        request.getSession().setAttribute("changeStatusMenuItem", resource.getString("changeStatusMenuItem"));
        request.getSession().setAttribute("showUsersMenuItem", resource.getString("showUsersMenuItem"));

        request.getSession().setAttribute("serviceTypeLabel", resource.getString("serviceTypeLabel"));
        request.getSession().setAttribute("tariffNameLabel", resource.getString("tariffNameLabel"));
        request.getSession().setAttribute("tariffPriceLabel", resource.getString("tariffPriceLabel"));
        request.getSession().setAttribute("addTariffButton", resource.getString("addTariffButton"));
        request.getSession().setAttribute("addTariffSuccess", resource.getString("addTariffSuccess"));
        request.getSession().setAttribute("addTariffExists", resource.getString("addTariffExists"));
        request.getSession().setAttribute("addTariffInvalid", resource.getString("addTariffInvalid"));

        request.getSession().setAttribute("deleteTariffButton", resource.getString("deleteTariffButton"));
        request.getSession().setAttribute("deleteTariffSuccess", resource.getString("deleteTariffSuccess"));
        request.getSession().setAttribute("deleteTariffNoTariffs", resource.getString("deleteTariffNoTariffs"));

        request.getSession().setAttribute("editTariffButton", resource.getString("editTariffButton"));
        request.getSession().setAttribute("editTariffSuccess", resource.getString("editTariffSuccess"));
        request.getSession().setAttribute("editTariffInvalid", resource.getString("editTariffInvalid"));

        request.getSession().setAttribute("subscriberNameLabel", resource.getString("subscriberNameLabel"));
        request.getSession().setAttribute("registerUserButton", resource.getString("registerUserButton"));
        request.getSession().setAttribute("registerUserSuccess", resource.getString("registerUserSuccess"));
        request.getSession().setAttribute("registerUserInvalid", resource.getString("registerUserInvalid"));

        request.getSession().setAttribute("subscriberLoginLabel", resource.getString("subscriberLoginLabel"));
        request.getSession().setAttribute("subscriberStatusLabel", resource.getString("subscriberStatusLabel"));
        request.getSession().setAttribute("updateStatusButton", resource.getString("updateStatusButton"));
        request.getSession().setAttribute("unblockStatus", resource.getString("unblockStatus"));
        request.getSession().setAttribute("blockStatus", resource.getString("blockStatus"));
        request.getSession().setAttribute("updateStatusSuccess", resource.getString("updateStatusSuccess"));
        request.getSession().setAttribute("updateStatusNotExist", resource.getString("updateStatusNotExist"));
        request.getSession().setAttribute("updateStatusInvalid", resource.getString("updateStatusInvalid"));

        request.getSession().setAttribute("activeStatusLabel", resource.getString("activeStatusLabel"));
        request.getSession().setAttribute("balanceLabel", resource.getString("balanceLabel"));

        request.getSession().setAttribute("chooseTariffMenuItem", resource.getString("chooseTariffMenuItem"));
        request.getSession().setAttribute("depositMenuItem", resource.getString("depositMenuItem"));
        request.getSession().setAttribute("showProfileMenuItem", resource.getString("showProfileMenuItem"));

        request.getSession().setAttribute("chooseTariffNoTariffs", resource.getString("chooseTariffNoTariffs"));
        request.getSession().setAttribute("chooseTariffBlocked", resource.getString("chooseTariffBlocked"));
        request.getSession().setAttribute("chooseTariffIsChosen", resource.getString("chooseTariffIsChosen"));
        request.getSession().setAttribute("chooseTariffSuccess", resource.getString("chooseTariffSuccess"));
        request.getSession().setAttribute("chooseTariffNoMoney", resource.getString("chooseTariffNoMoney"));

        request.getSession().setAttribute("depositSumLabel", resource.getString("depositSumLabel"));
        request.getSession().setAttribute("depositSumInvalid", resource.getString("depositSumInvalid"));
        request.getSession().setAttribute("depositSuccessActive", resource.getString("depositSuccessActive"));
        request.getSession().setAttribute("depositSuccessBlocked", resource.getString("depositSuccessBlocked"));

        request.getSession().setAttribute("userTariffsLabel", resource.getString("userTariffsLabel"));

        return "/login";
    }

    private String getLanguage(HttpServletRequest request) {
        String language = request.getParameter("lang");
        if (language == null || language.isEmpty()) {
            language = (String) request.getSession().getAttribute("lang");
        }
        if (language == null || language.isEmpty()) {
            language = "eng";
        }
        return language;
    }
}
