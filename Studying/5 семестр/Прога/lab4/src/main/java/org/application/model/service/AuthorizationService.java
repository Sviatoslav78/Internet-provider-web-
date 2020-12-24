package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;

public class AuthorizationService {
    SubscriberDao subscriberDao;

    public AuthorizationService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public Subscriber isValidAuth(String login, String password) {
        Subscriber subscriber = subscriberDao.getByLogin(login);
        if (subscriber.getLogin().equals("EMPTY")) {
            return Subscriber.EMPTY;
        } else {
            if (password.equals(subscriber.getPassword())) {
                return subscriber;
            }
        }
        return Subscriber.EMPTY;
    }
}
