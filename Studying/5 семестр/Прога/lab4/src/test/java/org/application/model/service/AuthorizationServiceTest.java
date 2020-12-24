package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AuthorizationServiceTest {
    SubscriberDao subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());

    @Before
    public void prepareData() {
        subscriberDao.save(new Subscriber("testUser", "loginForTest", "passwordForTest"));
    }

    @Test
    public void testIsValidAuthReal() {
        AuthorizationService authorizationService = new AuthorizationService();
        Subscriber subscriber = authorizationService.isValidAuth("loginForTest", "passwordForTest");

        assertEquals("loginForTest", subscriber.getLogin());
    }

    @Test
    public void testIsValidAuthLogin() {
        AuthorizationService authorizationService = new AuthorizationService();
        Subscriber subscriber = authorizationService.isValidAuth("loginForTest", "NOT_CORRECT");

        assertEquals("EMPTY", subscriber.getLogin());
    }

    @Test
    public void testIsValidAuthPassword() {
        AuthorizationService authorizationService = new AuthorizationService();
        Subscriber subscriber = authorizationService.isValidAuth("NOT_CORRECT", "passwordForTest");

        assertEquals("EMPTY", subscriber.getLogin());
    }

    @After
    public void deleteTestData() {
        subscriberDao.deleteByLogin("loginForTest");
    }

}