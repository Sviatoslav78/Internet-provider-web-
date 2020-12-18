package org.application.model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JdbcDao<T, K> implements AbstractDao<T, K> {
    protected Connection connection;
    protected Statement statement;

    public JdbcDao(Connection connection) {
        try {
            this.connection = connection;
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void execSQL (String sql){
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected long getMaxFieldValue(String table, String field) throws SQLException {
        String sql = "select max(${field}) from "  + table;
        sql = sql.replace("${field}", field);

        try (ResultSet rs = statement.executeQuery(sql)) {
            boolean hasFirst = rs.first();
            if (hasFirst) {
                String fieldName = "max(" + field + ")" ;
                long maxId = rs.getLong(fieldName);
                return maxId;
            } else {
                return -1;
            }
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
