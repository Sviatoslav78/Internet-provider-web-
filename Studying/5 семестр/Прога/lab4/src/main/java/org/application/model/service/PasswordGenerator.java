package org.application.model.service;

public class PasswordGenerator {
    private static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String generatePassword() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            stringBuilder.append(getSymbol());
        }
        return stringBuilder.toString();
    }

    private static char getSymbol() {
        int index = (int) (Math.random() * SYMBOLS.length());
        return SYMBOLS.charAt(index);
    }
}
