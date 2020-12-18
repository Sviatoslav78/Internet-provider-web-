package org.application.model.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionImpl implements ConnectionFactory {
    private static Connection connection;
    private static volatile ConnectionImpl instance;

    private ConnectionImpl() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionImpl getInstance() {
        if (instance == null) {
            synchronized (ConnectionImpl.class) {
                if (instance == null)
                    instance = new ConnectionImpl();
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
