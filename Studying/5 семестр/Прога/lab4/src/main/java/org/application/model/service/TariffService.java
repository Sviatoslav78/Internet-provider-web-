package org.application.model.service;

import org.application.model.db.SubscriberTariffDao;
import org.application.model.db.TariffDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.ServiceType;
import org.application.model.entity.SubscriberTariff;
import org.application.model.entity.Tariff;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TariffService {
    private TariffDao tariffDao;
    private SubscriberTariffDao subscriberTariffDao;

    public TariffService() {
        tariffDao = new TariffDao(ConnectionImpl.getInstance().getConnection());
        subscriberTariffDao = new SubscriberTariffDao(ConnectionImpl.getInstance().getConnection());
    }

    public boolean addTariff(Tariff tariff) {
        if (tariffDao.getByName(tariff.getName()).getName().equals("EMPTY")) {
            tariffDao.save(tariff);
            return true;
        }
        return false;
    }

    public void deleteTariff(String name) {
        tariffDao.deleteByName(name);
        for (SubscriberTariff subscriberTariff : subscriberTariffDao.getAll()) {
            if (subscriberTariff.getTariffName().equals(name))
                subscriberTariffDao.deleteById(subscriberTariff.getId());
        }

    }

    public void changeTariffPrice(String name, int newPrice) {
        tariffDao.update(new Tariff(ServiceType.EMPTY, name, newPrice));
    }

    public List<Tariff> getTariffsAsc() {
        List<Tariff> tariffList = tariffDao.getAll();

        Collections.sort(tariffList, Comparator.comparing(Tariff::getName));
        return tariffList;
    }
}
