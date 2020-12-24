package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.SubscriberTariffDao;
import org.application.model.db.TariffDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.ServiceType;
import org.application.model.entity.Subscriber;
import org.application.model.entity.SubscriberTariff;
import org.application.model.entity.Tariff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChooseTariffServiceTest {
    private SubscriberDao subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    private TariffDao tariffDao = new TariffDao(ConnectionImpl.getInstance().getConnection());

    @Before
    public void prepareTariff() {
        tariffDao.save(new Tariff(ServiceType.TV, "tariffForTest", 100.0));
    }

    @Test
    public void chooseTariffForInactiveUser() {
        subscriberDao.save(new Subscriber(false, 150.0, "loginForTest"));

        ChooseTariffService chooseTariffService = new ChooseTariffService();
        String response = chooseTariffService.chooseTariff("tariffForTest", "loginForTest");

        assertEquals("inactive", response);
    }

    @Test
    public void chooseTariffSuccessfully() {
        subscriberDao.save(new Subscriber(true, 150.0, "loginForTest"));

        ChooseTariffService chooseTariffService = new ChooseTariffService();
        String response = chooseTariffService.chooseTariff("tariffForTest", "loginForTest");

        assertEquals("ok", response);
    }

    @Test
    public void chooseTariffAndBlock() {
        subscriberDao.save(new Subscriber(true, 50.0, "loginForTest"));

        ChooseTariffService chooseTariffService = new ChooseTariffService();
        String response = chooseTariffService.chooseTariff("tariffForTest", "loginForTest");

        assertEquals("noMoney", response);
    }

    @Test
    public void chooseSameTariff() {
        subscriberDao.save(new Subscriber(true, 150.0, "loginForTest"));

        ChooseTariffService chooseTariffService = new ChooseTariffService();
        chooseTariffService.chooseTariff("tariffForTest", "loginForTest");
        String response = chooseTariffService.chooseTariff("tariffForTest", "loginForTest");

        assertEquals("sameTariff", response);
    }

    @After
    public void deleteTestData() {
        subscriberDao.deleteByLogin("loginForTest");
        tariffDao.deleteByName("tariffForTest");

        SubscriberTariffDao subscriberTariffDao = new SubscriberTariffDao(ConnectionImpl.getInstance().getConnection());

        for (SubscriberTariff subscriberTariff : subscriberTariffDao.getAll()) {
            if (subscriberTariff.getSubscriberLogin().equals("loginForTest") &&
                    subscriberTariff.getTariffName().equals("tariffForTest")) {
                subscriberTariffDao.deleteById(subscriberTariff.getId());
            }
        }
    }
}