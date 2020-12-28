package org.application.model.entity;

public class Tariff {
    private ServiceType serviceType;
    private String name;
    private double price;
    private String formattedPrice;

    public static Tariff EMPTY;

    static {
        EMPTY = new Tariff(ServiceType.EMPTY, "EMPTY", -1);
    }

    public Tariff() {
    }

    public Tariff(ServiceType serviceType, String name, double price) {
        this.serviceType = serviceType;
        this.name = name;
        this.price = price;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    @Override
    public String toString() {
        return "Service type: " + serviceType + "\n" +
                "Name: " + name + "\n" +
                "Price: " + price + "\n";
    }
}
