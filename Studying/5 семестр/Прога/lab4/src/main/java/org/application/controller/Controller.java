package org.application.controller;

import org.application.model.entity.Subscriber;
import org.application.view.View;

public class Controller {
//    View view;
//    Subscriber lastAuthorizedUser;
//    ManagerCommands managerCommands;
//
//    public Controller() {
//        view = new View();
//        lastAuthorizedUser = new Subscriber();
//        managerCommands = new ManagerCommands();
//    }
//
//    public void run() {
//        int mainCommand;
//
//        view.showMainMenu();
//        mainCommand = view.getCommand("3");
//        executeCommands(mainCommand);
//    }
//
//    private void executeCommands(int mainCommand) {
//        String stringCommand;
//
//        switch (mainCommand) {
//            case 1:
//                stringCommand = view.getAuthData();
//                lastAuthorizedUser.setLogin(stringCommand.split(" ")[0]);
//                managerCommands.getCommand("authorization").execute(view, stringCommand);
//
//                if (lastAuthorizedUser.getLogin().equals("admin123")) {
//                    executeAdminCommands();
//                } else {
//                    executeUserCommands();
//                }
//                break;
//            case 2:
//                // guest
//                break;
//            case 3:
//                System.exit(0);
//        }
//    }
//
//    private void executeAdminCommands() {
//        int intCommand;
//        view.showMainAdminMenu();
//        intCommand = view.getCommand("3");
//
//        switch (intCommand) {
//            case 1:
//                editTariffCommands();
//                break;
//            case 2:
//                editUserCommands();
//                break;
//            case 3:
//                run();
//        }
//    }
//
//    private void editTariffCommands() {
//        String stringCommand;
//        view.showEditTariffsMenu();
//
//        switch (view.getCommand("5")) {
//            case 1:
//                stringCommand = view.getTariffToAdd();
//                managerCommands.getCommand("addTariff").execute(view, stringCommand);
//                editTariffCommands();
//            case 2:
//                stringCommand = view.getTariffToDelete();
//                managerCommands.getCommand("deleteTariff").execute(view, stringCommand);
//                editTariffCommands();
//            case 3:
//                stringCommand = view.getNewTariffPrice();
//                managerCommands.getCommand("editTariff").execute(view, stringCommand);
//                editTariffCommands();
//            case 4:
//                stringCommand = view.getSortType();
//                managerCommands.getCommand("showTariffs").execute(view, stringCommand);
//                editTariffCommands();
//            case 5:
//                executeAdminCommands();
//        }
//    }
//
//    private void editUserCommands() {
//        String stringCommand;
//        view.showEditSubscribersMenu();
//
//        switch (view.getCommand("5")) {
//            case 1:
//                stringCommand = view.getUserNameToRegister();
//                managerCommands.getCommand("register").execute(view, stringCommand);
//                editUserCommands();
//            case 2:
//                stringCommand = view.getUserToBlock();
//                managerCommands.getCommand("changeStatus").execute(view, stringCommand);
//                editUserCommands();
//            case 3:
//                stringCommand = view.getUserToUnblock();
//                managerCommands.getCommand("changeStatus").execute(view, stringCommand);
//                editUserCommands();
//            case 4:
//                stringCommand = view.getSortType();
//                managerCommands.getCommand("showUsers").execute(view, stringCommand);
//                editTariffCommands();
//            case 5:
//                executeAdminCommands();
//        }
//    }
//
//    private void executeUserCommands() {
//        String stringCommand;
//        view.showMainUserMenu();
//
//        switch (view.getCommand("5")) {
//            case 1:
//                stringCommand = view.getSortType();
//                managerCommands.getCommand("showTariffs").execute(view, stringCommand);
//                executeUserCommands();
//            case 2:
//                stringCommand = view.getTariffToChoose();
//                stringCommand += " " + lastAuthorizedUser.getLogin();
//                managerCommands.getCommand("chooseTariff").execute(view, stringCommand);
//                executeUserCommands();
//                break;
//            case 3:
//                stringCommand = view.getDepositSum();
//                stringCommand += " " + lastAuthorizedUser.getLogin();
//                managerCommands.getCommand("deposit").execute(view, stringCommand);
//                executeUserCommands();
//                break;
//            case 4:
//                managerCommands.getCommand("profileInfo").execute(view, lastAuthorizedUser.getLogin());
//            case 5:
//                run();
//        }
//    }
}
