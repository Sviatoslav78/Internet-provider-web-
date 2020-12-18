package org.application.controller;

public class Validator {

    public static boolean isValidLogin(String login) {
        return login.matches("^[A-Za-z]+[0-9]+$");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("[A-Za-z0-9]{8}");
    }

    public static boolean isValidTariffName(String tariffName) {
        return (tariffName.matches("[A-Za-z0-9-\\s]+") && !tariffName.trim().isEmpty());
    }

    public static boolean isValidSubscriberName(String name) {
        return name.matches("[A-Za-z-]+");
    }
}
