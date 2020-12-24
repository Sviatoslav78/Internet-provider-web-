package org.application.model.db;

import org.application.model.entity.SubscriberTariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberTariffDao extends JdbcDao<SubscriberTariff, Long> {
    public SubscriberTariffDao(Connection connection) {
        super(connection);
        String SubsTariffTable = "CREATE TABLE IF NOT EXISTS subscriber_tariff\n" +
                "  (id int auto_increment primary key,\n" +
                "  subscriber_login varchar(50),\n" +
                "  tariff_name varchar(50))";
        execSQL(SubsTariffTable);
    }

    @Override
    public void save(SubscriberTariff subscriberTariff) {
        PreparedStatement saveSt;
        try {
            saveSt = connection.prepareStatement("insert into subscriber_tariff (subscriber_login, tariff_name) " +
                    "values (?,?)");

            saveSt.setString(1, subscriberTariff.getSubscriberLogin());
            saveSt.setString(2, subscriberTariff.getTariffName());

            saveSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SubscriberTariff getByID(Long id) {
        return SubscriberTariff.EMPTY;
    }

    @Override
    public List<SubscriberTariff> getAll() {
        List<SubscriberTariff> list = new ArrayList<>();
        String sql = "select id, subscriber_login, tariff_name from subscriber_tariff";
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new SubscriberTariff(
                        rs.getLong("id"),
                        rs.getString("subscriber_login"),
                        rs.getString("tariff_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteById(Long id) {
        execSQL("delete from subscriber_tariff where id = " + id);
    }

    @Override
    public void update(SubscriberTariff subscriberTariff) {
    }
}
