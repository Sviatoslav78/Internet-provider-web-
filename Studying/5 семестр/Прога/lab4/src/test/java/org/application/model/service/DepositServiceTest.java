package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DepositServiceTest {
    private SubscriberDao subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());

    @Before
    public void prepareTestData() {
        subscriberDao.save(new Subscriber(-100.0, "loginForTest"));
    }

    @Test
    public void topUpAndUnblock() {
        DepositService depositService = new DepositService();
        boolean wasUnblocked = depositService.topUpAccount(100.0, "loginForTest");

        assertTrue(wasUnblocked);
    }

    @Test
    public void topUpAndStayBlocked() {
        DepositService depositService = new DepositService();
        boolean wasUnblocked = depositService.topUpAccount(50.0, "loginForTest");

        assertFalse(wasUnblocked);
    }

    @After
    public void deleteTestData() {
        subscriberDao.deleteByLogin("loginForTest");
    }
}