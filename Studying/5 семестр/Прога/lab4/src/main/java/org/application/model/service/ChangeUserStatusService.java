package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;

import java.sql.SQLException;

public class ChangeUserStatusService {
    SubscriberDao subscriberDao;

    public ChangeUserStatusService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public boolean changeUserStatus(String userLogin, boolean action) {
        if (!subscriberDao.getByLogin(userLogin).getLogin().equals("EMPTY")) {
            subscriberDao.update(new Subscriber(action, userLogin));
            return true;
        }
        return false;
    }
}
