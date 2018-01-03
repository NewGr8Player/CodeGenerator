package com.xavier.db;

import com.xavier.config.TypeMapping;
import com.xavier.db.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {

	private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

	protected Connection connection;
	protected TypeMapping typeMapping;

	public Database(Connection connection, TypeMapping typeMapping) {
		this.connection = connection;
		this.typeMapping = typeMapping;
	}

	/**
	 * 获得表模型
	 *
	 * @param catalog
	 * @param schema
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public abstract Table getTable(String catalog, String schema, String tableName) throws SQLException;

	public Connection getConnection() {
		return connection;
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			LOGGER.info(e.getMessage(), e);
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			LOGGER.info(e.getMessage(), e);
		}
	}
}
