package org.application.model.service;

import org.application.model.db.TariffDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.ServiceType;
import org.application.model.entity.Tariff;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TariffService {
    private TariffDao tariffDao;

    public TariffService() {
        tariffDao = new TariffDao(ConnectionImpl.getInstance().getConnection());
    }

    public boolean addTariff(Tariff tariff) {
        if (tariffDao.getByName(tariff.getName()).getName().equals("EMPTY")) {
            tariffDao.save(tariff);
            return true;
        }
        return false;
    }

    public boolean deleteTariff(String name) {
        if (!tariffDao.getByName(name).getName().equals("EMPTY")) {
            tariffDao.deleteByName(name);
            return true;
        }
        return false;
    }

    public boolean changeTariffPrice(String name, int newPrice) {
        if (!tariffDao.getByName(name).getName().equals("EMPTY")) {
            tariffDao.update(new Tariff(ServiceType.EMPTY, name, newPrice));
            return true;
        }
        return false;
    }

    public List<Tariff> getTariffsAsc() {
        List<Tariff> tariffList = tariffDao.getAll();

        Collections.sort(tariffList, Comparator.comparing(Tariff::getName));
        return tariffList;
    }

    public List<Tariff> getTariffsDesc() {
        List<Tariff> tariffList = getTariffsAsc();

        Collections.reverse(tariffList);
        return  tariffList;
    }

    public List<Tariff> getTariffsByPrice() {
        List<Tariff> tariffList = tariffDao.getAll();

        Collections.sort(tariffList, Comparator.comparing(Tariff::getPrice));
        return tariffList;
    }
}
