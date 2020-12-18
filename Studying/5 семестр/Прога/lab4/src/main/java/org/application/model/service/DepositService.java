package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;

import java.sql.SQLException;

public class DepositService {
    private SubscriberDao subscriberDao;

    public DepositService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public boolean topUpAccount(double depositSum, String userLogin) {
        Subscriber currentSubscriber = subscriberDao.getByLogin(userLogin);

        currentSubscriber.setBalance(currentSubscriber.getBalance() + depositSum);
        subscriberDao.changeBalance(currentSubscriber);

        if (currentSubscriber.getBalance() >= 0) {
            currentSubscriber.setActive(true);
            subscriberDao.update(currentSubscriber);
            return true;
        }
        return false;
    }
}
