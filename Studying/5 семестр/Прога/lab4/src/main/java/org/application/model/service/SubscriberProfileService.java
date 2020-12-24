package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.SubscriberTariffDao;
import org.application.model.db.TariffDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;
import org.application.model.entity.SubscriberTariff;
import org.application.model.entity.Tariff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubscriberProfileService {
    SubscriberDao subscriberDao;
    private SubscriberTariffDao subscriberTariffDao;
    private TariffDao tariffDao;

    public SubscriberProfileService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
        subscriberTariffDao = new SubscriberTariffDao(ConnectionImpl.getInstance().getConnection());
        tariffDao = new TariffDao(ConnectionImpl.getInstance().getConnection());
    }

    public Subscriber getUserInfo(String login) {
        return subscriberDao.getByLogin(login);
    }

    public List<Subscriber> getAllUsersAsc() {
        List<Subscriber> subscriberList = subscriberDao.getAll();

        Collections.sort(subscriberList, Comparator.comparing(Subscriber::getName));
        return subscriberList;
    }

    public List<Tariff> getSubscriberTariffs(String userLogin) {
        List<Tariff> subscriberTariffs = new ArrayList<>();
        for (SubscriberTariff subscriberTariff : subscriberTariffDao.getAll()) {
            if (subscriberTariff.getSubscriberLogin().equals(userLogin)) {
                Tariff tempTariff = tariffDao.getByName(subscriberTariff.getTariffName());
                if (!tempTariff.getName().equals("EMPTY")) {
                    subscriberTariffs.add(tempTariff);
                }
            }
        }
        return subscriberTariffs;
    }
}
