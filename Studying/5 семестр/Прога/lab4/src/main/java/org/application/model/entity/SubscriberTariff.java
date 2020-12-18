package org.application.model.entity;

public class SubscriberTariff {
    private long id;
    private String subscriberLogin;
    private String tariffName;
    public static SubscriberTariff EMPTY;

    static {
        EMPTY = new SubscriberTariff(-1, "EMPTY", "EMPTY");
    }

    public SubscriberTariff(String subscriberLogin, String tariffName) {
        this.subscriberLogin = subscriberLogin;
        this.tariffName = tariffName;
    }

    public SubscriberTariff(long id, String subscriberLogin, String tariffName) {
        this.id = id;
        this.subscriberLogin = subscriberLogin;
        this.tariffName = tariffName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubscriberLogin() {
        return subscriberLogin;
    }

    public void setSubscriberLogin(String subscriberLogin) {
        this.subscriberLogin = subscriberLogin;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    @Override
    public String toString() {
        return "SubscriberTariff{" +
                "subscriberId=" + subscriberLogin +
                ", tariffId=" + tariffName +
                '}';
    }
}
