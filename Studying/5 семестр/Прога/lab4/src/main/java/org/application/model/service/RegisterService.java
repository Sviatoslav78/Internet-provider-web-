package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;


public class RegisterService {
    SubscriberDao subscriberDao;

    public RegisterService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public Subscriber registerUser(String newUserName) {
        String login = newUserName + subscriberDao.getMaxSubscriber();
        String password = PasswordGenerator.generatePassword();

        Subscriber newSubscriber = new Subscriber(newUserName, login, password);
        subscriberDao.save(newSubscriber);
        return newSubscriber;
    }
}
