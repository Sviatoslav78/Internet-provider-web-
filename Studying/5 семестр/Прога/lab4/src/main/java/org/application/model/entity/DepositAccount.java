package org.application.model.entity;

public class DepositAccount {
    private long id;
    private double balance;
    public static DepositAccount EMPTY;

    static {
        EMPTY = new DepositAccount(-1, -1);
    }

    public DepositAccount() {
    }

    public DepositAccount(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "DepositAccount{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
