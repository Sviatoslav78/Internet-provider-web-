package org.application.view;

public class View {
    private InputOfData inputOfData;

    public View() {
        inputOfData = new InputOfData(this);
    }

    public void showMainMenu() {
        System.out.println("1. Sign in\n" +
                "2. Continue as a guest\n" +
                "3. Exit\n");
    }

    public void showMainAdminMenu() {
        System.out.println("1. Edit tariffs\n" +
                "2. Edit subscribers\n" +
                "3. Exit\n");
    }

    public void showEditTariffsMenu() {
        System.out.println("1. Add tariff\n" +
                "2. Delete tariff\n" +
                "3. Edit tariff\n" +
                "4. Show all tariffs\n" +
                "5. Exit\n");
    }

    public void showEditSubscribersMenu() {
        System.out.println("1. Register user\n" +
                "2. Block user\n" +
                "3. Unblock user\n" +
                "4. Show all users\n" +
                "5. Exit\n");
    }

    public void showMainUserMenu() {
        System.out.println("1. Show available tariffs\n" +
                "2. Choose tariff\n" +
                "3. Top up an account\n" +
                "4. Show profile info\n" +
                "5. Exit\n");
    }

    public int getCommand(String menuItemsNumber) {
        int menuItem;

        while (true) {
            menuItem = inputOfData.inputCommand(menuItemsNumber);
            if (menuItem != -1)
                return menuItem;
        }
    }

    public String getAuthData() {
        return inputOfData.inputAuthData();
    }

    public String getTariffToAdd() {
        return inputOfData.inputTariffToAdd();
    }

    public String getTariffToDelete() {
        return inputOfData.inputTariffToDelete();
    }

    public String getNewTariffPrice() {
        return inputOfData.inputTariffPrice();
    }

    public String getUserNameToRegister() {
        return inputOfData.inputUserName();
    }

    public String getUserToBlock() {
        return "block " + inputOfData.inputUserLogin("block");
    }

    public String getUserToUnblock() {
        return "unblock " + inputOfData.inputUserLogin("unblock");
    }

    public String getSortType() {
        System.out.println("1. Show by name(ascending)\n" +
                "2. Show by name(descending)\n" +
                "3. Show by sum\n");
        int intCommand = getCommand("3");
        switch (intCommand) {
            case 1:
                return "asc";
            case 2:
                return "desc";
            case 3:
                return "price";
            default:
                return "";
        }
    }

    public String getTariffToChoose() {
        return inputOfData.inputTariffToChoose();
    }

    public String getDepositSum() {
        return String.valueOf(inputOfData.inputDepositSum());
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void showError(String error) {
        System.err.println(error);
    }

}
