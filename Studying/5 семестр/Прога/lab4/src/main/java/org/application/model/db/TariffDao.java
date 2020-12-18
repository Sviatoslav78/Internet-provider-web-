package org.application.model.db;

import org.application.model.entity.ServiceType;
import org.application.model.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffDao extends JdbcDao<Tariff, Long> {

    public TariffDao(Connection connection) {
        super(connection);
        String tariffTable = "CREATE TABLE IF NOT EXISTS tariff\n" +
                " (id int auto_increment primary key,\n" +
                " service_type varchar(100),\n" +
                " name varchar(100),\n" +
                " price double);";

        execSQL(tariffTable);
    }

    @Override
    public void save(Tariff tariff) {
        PreparedStatement saveSt;
        try {
            saveSt = connection.prepareStatement("insert into tariff (service_type, name, price) " +
                    "values (?,?,?)");

            saveSt.setString(1, String.valueOf(tariff.getServiceType()));
            saveSt.setString(2, tariff.getName());
            saveSt.setDouble(3, tariff.getPrice());

            saveSt.executeUpdate();

//            long maxTariffId = getMaxFieldValue("tariff", "id");
//            tariff.setId(maxTariffId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tariff getByID(Long id) {
//        PreparedStatement getByIdSt = null;
//        try {
//            getByIdSt = connection.prepareStatement("select service_type, name, price " +
//                    "from tariff where id =?");
//
//            getByIdSt.setLong(1, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try (ResultSet rs = getByIdSt.executeQuery()) {
//            if (rs.first()) {
//                return new Tariff(id,
//                        ServiceType.valueOf(rs.getString("service_type")),
//                        rs.getString("name"),
//                        rs.getDouble("price"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return Tariff.EMPTY;
    }

    public Tariff getByName(String name) {
        PreparedStatement getByNameSt = null;
        try {
            getByNameSt = connection.prepareStatement("select service_type, price " +
                    "from tariff where name =?");

            getByNameSt.setString(1, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = getByNameSt.executeQuery()) {
            if (rs.first()) {
                return new Tariff(
                        ServiceType.valueOf(rs.getString("service_type")),
                        name,
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Tariff.EMPTY;
    }

    @Override
    public List<Tariff> getAll() {
        List<Tariff> list = new ArrayList<>();
        String sql = "select service_type, name, price from tariff";
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Tariff(
                        ServiceType.valueOf(rs.getString("service_type")),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteById(Long id) {
        execSQL("delete from tariff where id = " + id);
    }

    public void deleteByName(String name) {
        execSQL("delete from tariff where name = '" + name + "'");
    }

    @Override
    public void update(Tariff tariff) {
        PreparedStatement updateSt;

        try {
            updateSt = connection.prepareStatement("update tariff " +
                    "set price = ? where name = ?");

            updateSt.setDouble(1, tariff.getPrice());
            updateSt.setString(2, tariff.getName());

            updateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
