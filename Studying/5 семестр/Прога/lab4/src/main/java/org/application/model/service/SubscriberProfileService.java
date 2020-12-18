package org.application.model.service;

import org.application.model.db.SubscriberDao;
import org.application.model.db.connection.ConnectionImpl;
import org.application.model.entity.Subscriber;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubscriberProfileService {
    SubscriberDao subscriberDao;

    public SubscriberProfileService() {
        subscriberDao = new SubscriberDao(ConnectionImpl.getInstance().getConnection());
    }

    public Subscriber getUserInfo(String login) {
        return subscriberDao.getByLogin(login);
    }

    public List<Subscriber> getAllUsersAsc() {
        List<Subscriber> subscriberList = subscriberDao.getAll();

        Collections.sort(subscriberList, Comparator.comparing(Subscriber::getName));
        return subscriberList;
    }

    public List<Subscriber> getAllUsersDesc() {
        List<Subscriber> subscriberList = getAllUsersAsc();

        Collections.reverse(subscriberList);
        return  subscriberList;
    }

    public List<Subscriber> getAllUsersByBalance() {
        List<Subscriber> subscriberList = subscriberDao.getAll();

        Collections.sort(subscriberList, Comparator.comparing(Subscriber::getBalance));
        return subscriberList;
    }
}
