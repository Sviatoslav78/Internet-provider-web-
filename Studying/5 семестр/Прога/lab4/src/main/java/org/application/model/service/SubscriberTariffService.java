package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.SubscriberTariffDao;
import org.application.model.db.TariffDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;
import org.application.model.entity.SubscriberTariff;
import org.application.model.entity.Tariff;

import java.sql.SQLException;

public class SubscriberTariffService {
    private SubscriberTariffDao subscriberTariffDao;
    private TariffDao tariffDao;
    private SubscriberDao subscriberDao;

    public SubscriberTariffService() {
        subscriberTariffDao = new SubscriberTariffDao(ConnectionImpl.getInstance().getConnection());
        tariffDao = new TariffDao(ConnectionImpl.getInstance().getConnection());
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public String chooseTariff(String tariffName, String userLogin) {
        Tariff tariff = tariffDao.getByName(tariffName);
        Subscriber subscriber = subscriberDao.getByLogin(userLogin);

        if (!subscriber.isActive()) {
            return "inactive";
        }

        if (tariff.getName().equals("EMPTY")) {
            return "noTariff";
        } else {
            if (subscriber.getBalance() - tariff.getPrice() > 0) {
                subscriberTariffDao.save(new SubscriberTariff(userLogin, tariffName));
                subscriber.setBalance(subscriber.getBalance() - tariff.getPrice());
                subscriberDao.changeBalance(subscriber);
                return "ok";
            }
        }
        subscriber.setActive(false);
        subscriberDao.update(subscriber);

        subscriberTariffDao.save(new SubscriberTariff(userLogin, tariffName));
        subscriber.setBalance(subscriber.getBalance() - tariff.getPrice());
        subscriberDao.changeBalance(subscriber);

        return "noMoney";

    }
}
