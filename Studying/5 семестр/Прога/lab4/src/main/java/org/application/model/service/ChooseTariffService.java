package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.SubscriberTariffDao;
import org.application.model.db.TariffDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;
import org.application.model.entity.SubscriberTariff;
import org.application.model.entity.Tariff;

import java.util.ArrayList;
import java.util.List;

public class ChooseTariffService {
    private SubscriberTariffDao subscriberTariffDao;
    private TariffDao tariffDao;
    private SubscriberDao subscriberDao;

    public ChooseTariffService() {
        subscriberTariffDao = new SubscriberTariffDao(ConnectionImpl.getInstance().getConnection());
        tariffDao = new TariffDao(ConnectionImpl.getInstance().getConnection());
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public String chooseTariff(String tariffName, String userLogin) {
        Tariff tariffToChoose = tariffDao.getByName(tariffName);
        Subscriber subscriber = subscriberDao.getByLogin(userLogin);
        List<SubscriberTariff> allSubscribersTariffs;

        if (!subscriber.isActive()) {
            return "inactive";
        } else {
            allSubscribersTariffs = subscriberTariffDao.getAll();

            for (SubscriberTariff subscriberTariff : allSubscribersTariffs) {
                if (subscriberTariff.getSubscriberLogin().equals(userLogin) &&
                        subscriberTariff.getTariffName().equals(tariffName)) {
                    return "sameTariff";
                }
            }
        }
        deleteOldTariffOfType(userLogin, tariffToChoose, allSubscribersTariffs);

        if (subscriber.getBalance() - tariffToChoose.getPrice() >= 0) {
            addTariffToDb(tariffName, userLogin, tariffToChoose, subscriber);
            return "ok";
        } else {
            subscriber.setActive(false);
            subscriberDao.update(subscriber);

            addTariffToDb(tariffName, userLogin, tariffToChoose, subscriber);
            return "noMoney";
        }
    }

    private void deleteOldTariffOfType(String userLogin, Tariff tariffToChoose, List<SubscriberTariff> allSubscribersTariffs) {
        List<Tariff> subscriberTariffs = new ArrayList<>();
        for (SubscriberTariff subscriberTariff : allSubscribersTariffs) {
            if (subscriberTariff.getSubscriberLogin().equals(userLogin)) {
                Tariff tempTariff = tariffDao.getByName(subscriberTariff.getTariffName());
                if (!tempTariff.getName().equals("EMPTY")) {
                    subscriberTariffs.add(tempTariff);
                }
            }
        }
        Tariff tariffToDelete = null;
        for (Tariff t :subscriberTariffs) {
            if (tariffToChoose.getServiceType().equals(t.getServiceType())) {
                tariffToDelete = t;
            }
        }
        if (tariffToDelete != null) {
            for (SubscriberTariff subscriberTariff : allSubscribersTariffs) {
                if (tariffToDelete.getName().equals(subscriberTariff.getTariffName())) {
                    subscriberTariffDao.deleteById(subscriberTariff.getId());
                }
            }
        }
    }

    private void addTariffToDb(String tariffName, String userLogin, Tariff tariff, Subscriber subscriber) {
        subscriberTariffDao.save(new SubscriberTariff(userLogin, tariffName));
        subscriber.setBalance(subscriber.getBalance() - tariff.getPrice());
        subscriberDao.changeBalance(subscriber);
    }

}
