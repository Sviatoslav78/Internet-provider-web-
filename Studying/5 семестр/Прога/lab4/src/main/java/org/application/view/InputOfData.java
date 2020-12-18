package org.application.view;

import org.application.model.entity.ServiceType;

import java.util.Scanner;

public class InputOfData {
    private Scanner scanner;
    private View view;
    private static final int INVALID_COMMAND = -1;

    public InputOfData(View view) {
        scanner = new Scanner(System.in);
        this.view = view;
    }

    public int inputCommand(String menuItemsNumber) {
        String stringCommand = scanner.nextLine();
        if (stringCommand.trim().matches("[1-" + menuItemsNumber + "]")) {
            return Integer.parseInt(stringCommand.trim());
        }
        view.showError("Menu item '" + stringCommand + "' is invalid, try again:");
        return INVALID_COMMAND;
    }

    public String inputAuthData() {
        String login;
        String password;

        while (true) {
            view.showMessage("Enter valid login:");
            login = scanner.nextLine();
            if (login.matches("^[A-Za-z]+[0-9]+$")) {
                break;
            } else {
                view.showError("Invalid login");
            }
        }

        while (true) {
            view.showMessage("Enter valid password:");
            password = scanner.nextLine();
            if (password.matches("[A-Za-z0-9]{8}")) {
                break;
            } else {
                view.showError("Invalid password");
            }
        }
        return login + " " + password;
    }

    public String inputTariffToAdd() {
        int serviceTypeNumber;
        String tariffName;
        int tariffPrice;
        int servicesNumber = ServiceType.values().length - 1;

        view.showMessage("Enter service type number:");
        for (int i = 0; i < servicesNumber; i++) {
            view.showMessage(i + 1 + ". " + ServiceType.values()[i]);
        }
        do {
            serviceTypeNumber = inputCommand(String.valueOf(servicesNumber)) - 1;
        } while (serviceTypeNumber == -2);

        while (true) {
            view.showMessage("Enter tariff name:");
            tariffName = scanner.nextLine();
            if (tariffName.matches("[A-Za-z0-9-\\s]+") &&
                    !tariffName.trim().isEmpty()) {
                break;
            } else {
                view.showError("Invalid tariff name");
            }
        }

        while (true) {
            view.showMessage("Enter price(integer) of the tariff:");
            try {
                tariffPrice = Integer.parseInt(scanner.nextLine());
                if (tariffPrice > 0) {
                    break;
                } else {
                    view.showError("Invalid price");
                }
            } catch (NumberFormatException e) {
                view.showError("Invalid price");
            }
        }
        return serviceTypeNumber + " " + tariffName + " " + tariffPrice;
    }

    public String inputTariffToDelete() {
        String tariffIdentifier;

        while (true) {
            view.showMessage("Enter tariff name to delete:");
            tariffIdentifier = scanner.nextLine();
            if (tariffIdentifier.matches("[A-Za-z0-9-\\s]+")) {
                break;
            } else {
                view.showError("Invalid tariff name");
            }
        }
        return tariffIdentifier;
    }

    public String inputTariffPrice() {
        String tariffIdentifier;
        int newTariffPrice;

        while (true) {
            view.showMessage("Enter tariff name to edit:");
            tariffIdentifier = scanner.nextLine();
            if (tariffIdentifier.matches("[A-Za-z0-9-_]+")) {
                break;
            } else {
                view.showError("Invalid tariff name");
            }
        }

        while (true) {
            view.showMessage("Enter new price(integer) of the tariff:");
            try {
                newTariffPrice = Integer.parseInt(scanner.nextLine());
                if (newTariffPrice > 0) {
                    break;
                } else {
                    view.showError("Invalid price");
                }
            } catch (NumberFormatException e) {
                view.showError("Invalid price");
            }
        }
        return tariffIdentifier + " " + newTariffPrice;
    }

    public String inputUserName() {
        String userName;
        while (true) {
            view.showMessage("Enter user's name to register:");
            userName = scanner.nextLine();
            if (userName.matches("[A-Za-z-]+")) {
                break;
            } else {
                view.showError("Invalid user's name");
            }
        }
        return userName;
    }

    public String inputUserLogin(String action) {
        String login;

        while (true) {
            view.showMessage("Enter user's login to " + action + ":");
            login = scanner.nextLine();
            if (login.matches("^[A-Za-z]+[0-9]+$")) {
                break;
            } else {
                view.showError("Invalid login");
            }
        }
        return login;
    }

    public String inputTariffToChoose() {
        String tariffName;

        while (true) {
            view.showMessage("Enter tariff name to choose:");
            tariffName = scanner.nextLine();
            if (tariffName.matches("[A-Za-z0-9-\\s]+") &&
                    !tariffName.trim().isEmpty()) {
                break;
            } else {
                view.showError("Invalid tariff name");
            }
        }
        return tariffName;
    }

    public double inputDepositSum() {
        double depositSum;

        while (true) {
            view.showMessage("Enter sum of deposit:");
            try {
                depositSum = Double.parseDouble(scanner.nextLine());
                if (depositSum > 0) {
                    break;
                } else {
                    view.showError("Invalid sum");
                }
            } catch (NumberFormatException e) {
                view.showError("Invalid sum");
            }
        }
        return depositSum;
    }
}
