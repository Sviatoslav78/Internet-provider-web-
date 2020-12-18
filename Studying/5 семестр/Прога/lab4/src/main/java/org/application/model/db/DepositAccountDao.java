package org.application.model.db;

import org.application.model.entity.DepositAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositAccountDao extends JdbcDao<DepositAccount, Long> {
    public DepositAccountDao(Connection connection) {
        super(connection);
        String depositTable = "  CREATE TABLE IF NOT EXISTS deposit_account\n" +
                "  (id int primary key,\n" +
                "  balance double)";
        execSQL(depositTable);
    }

    @Override
    public void save(DepositAccount depositAccount) {
        PreparedStatement saveSt;
        try {
            saveSt = connection.prepareStatement("insert into deposit_account (id, balance) " +
                    "values (?,?)");

            saveSt.setLong(1, depositAccount.getId());
            saveSt.setDouble(2, depositAccount.getBalance());

            saveSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DepositAccount getByID(Long id){
        PreparedStatement getByIdSt = null;
        try {
            getByIdSt = connection.prepareStatement("select balance " +
                    "from deposit_account where id =?");

            getByIdSt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet rs = getByIdSt.executeQuery()) {
            if (rs.first()) {
                return new DepositAccount(id,
                        rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DepositAccount.EMPTY;
    }

    @Override
    public List<DepositAccount> getAll() {
        List<DepositAccount> list = new ArrayList<>();
        String sql = "select id, balance from deposit_account";
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new DepositAccount(
                        rs.getLong("id"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteById(Long id) {
        execSQL("delete from deposit_account where id = " + id);
    }

    @Override
    public void update(DepositAccount depositAccount) {
        PreparedStatement updateSt;
        try {
            updateSt = connection.prepareStatement("update deposit_account " +
                    "set balance = ? where id = ?");

            updateSt.setDouble(1, depositAccount.getBalance());
            updateSt.setLong(2, depositAccount.getId());

            updateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
