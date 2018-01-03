package com.xavier.db;

import com.xavier.config.TypeMapping;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseFactory {

	private DatabaseFactory() {

	}

	public static Database createDatabase(Connection connection, TypeMapping typeMapping) throws SQLException {
		String dbProduct = connection.getMetaData().getDatabaseProductName();
		if (dbProduct.toLowerCase().contains("oracle")) {
			return new OracleDatabase(connection, typeMapping);
		} else if (dbProduct.toLowerCase().contains("sql server")) {
			return new SqlServerDatabase(connection, typeMapping);
		} else if (dbProduct.toLowerCase().contains("mysql")) {
			return new MySqlDatabase(connection, typeMapping);
		} else {
			return new DefaultDatabase(connection, typeMapping);
		}
	}
}
