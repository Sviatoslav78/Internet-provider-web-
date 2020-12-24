package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;

public class ChangeUserStatusService {
    SubscriberDao subscriberDao;

    public ChangeUserStatusService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public boolean changeUserStatus(String userLogin, boolean action) {
        if (!subscriberDao.getByLogin(userLogin).getLogin().equals("EMPTY")) {
            subscriberDao.update(new Subscriber(action, 0.0, userLogin));
            return true;
        }
        return false;
    }
}
