package org.application.model.entity;


public class Subscriber {
    private String name;
    private boolean active;
    private Double balance;
    private String login;
    private String password;

    public static Subscriber EMPTY;

    static {
        EMPTY = new Subscriber("EMPTY", false, -1.0, "EMPTY", "EMPTY");
    }

    public Subscriber() {
    }

    public Subscriber(String name, boolean active, Double balance, String login, String password) {
        this.name = name;
        this.active = active;
        this.balance = balance;
        this.login = login;
        this.password = password;
    }

    public Subscriber(String name, String login, String password) {
        this.name = name;
        this.active = true;
        this.balance = 0.0;
        this.login = login;
        this.password = password;
    }

    public Subscriber(Double balance, String login) {
        this.balance = balance;
        this.login = login;
    }

    public Subscriber(boolean active, Double balance, String login) {
        this.active = active;
        this.balance = balance;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Active account: " + active + "\n" +
                "Balance: " + balance + "\n" +
                "Login: " + login + "\n";
    }
}
