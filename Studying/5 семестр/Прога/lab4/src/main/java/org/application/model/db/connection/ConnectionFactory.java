package org.application.model.db.connection;

import java.sql.Connection;

public interface ConnectionFactory {

    Connection getConnection();
}
