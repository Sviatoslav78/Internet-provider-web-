package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChangeUserStatusServiceTest {
    private SubscriberDao subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());

    @Before
    public void prepareTestData() {
        subscriberDao.save(new Subscriber(true, 0.0, "loginForTest"));
    }

    @Test
    public void changeUserStatus() {
        ChangeUserStatusService changeUserStatusService = new ChangeUserStatusService();

        boolean wasChanged = changeUserStatusService.changeUserStatus("loginForTest", false);
        assertTrue(wasChanged);
    }

    @Test
    public void changeUserStatusForEmpty() {
        ChangeUserStatusService changeUserStatusService = new ChangeUserStatusService();

        boolean wasChanged = changeUserStatusService.changeUserStatus("NOT_CORRECT", false);
        assertFalse(wasChanged);
    }

    @After
    public void deleteTestData() {
        subscriberDao.deleteByLogin("loginForTest");
    }
}