package org.application.model.db;

import org.application.model.entity.Subscriber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDao extends JdbcDao<Subscriber, Long> {

    public SubscriberDao(Connection connection) {
        super(connection);
        String clientTable = "CREATE TABLE IF NOT EXISTS subscriber\n" +
                "(id int auto_increment primary key,\n" +
                "name varchar(100),\n" +
                "active boolean,\n" +
                "balance double,\n" +
                "login varchar(100),\n" +
                "password varchar(100))";
        execSQL(clientTable);
    }

    @Override
    public void save(Subscriber subscriber) {
        PreparedStatement saveSt;
        try {
            saveSt = connection.prepareStatement("insert into subscriber (name, active, balance, login, password) " +
                    "values (?,?,?,?,?)");

            saveSt.setString(1, subscriber.getName());
            saveSt.setBoolean(2, subscriber.isActive());
            saveSt.setDouble(3, subscriber.getBalance());
            saveSt.setString(4, subscriber.getLogin());
            saveSt.setString(5, subscriber.getPassword());

            saveSt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subscriber getByID(Long id) {
        return Subscriber.EMPTY;
    }

    public Subscriber getByLogin(String login) {
        PreparedStatement getByLoginSt = null;
        try {
            getByLoginSt = connection.prepareStatement("select name, active, balance, password " +
                    "from subscriber where login =?");

            getByLoginSt.setString(1, login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet rs = getByLoginSt.executeQuery()) {
            if (rs.first()) {
                return new Subscriber(rs.getString("name"),
                        rs.getBoolean("active"),
                        rs.getDouble("balance"),
                        login,
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Subscriber.EMPTY;
    }

    @Override
    public List<Subscriber> getAll() {
        List<Subscriber> list = new ArrayList<>();
        String sql = "select name, active, balance, login, password from subscriber";
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Subscriber(
                        rs.getString("name"),
                        rs.getBoolean("active"),
                        rs.getDouble("balance"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteById(Long id) {
        execSQL("delete from subscriber where id = " + id);
    }

    public void deleteByLogin(String login) {
        execSQL("delete from subscriber where login = '" + login + "'");
    }

    @Override
    public void update(Subscriber subscriber) {
        PreparedStatement updateSt;
        try {
            updateSt = connection.prepareStatement("update subscriber " +
                    "set active = ? where login = ?");

            updateSt.setBoolean(1, subscriber.isActive());
            updateSt.setString(2, subscriber.getLogin());

            updateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeBalance(Subscriber subscriber) {
        PreparedStatement updateSt;
        try {
            updateSt = connection.prepareStatement("update subscriber " +
                    "set balance = ? where login = ?");

            updateSt.setDouble(1, subscriber.getBalance());
            updateSt.setString(2, subscriber.getLogin());

            updateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getMaxSubscriber() {
        long maxSubscriber;
        try {
            maxSubscriber = getMaxFieldValue("subscriber", "id");
        } catch (SQLException e) {
            return 0;
        }
        return maxSubscriber + 1;
    }
}
