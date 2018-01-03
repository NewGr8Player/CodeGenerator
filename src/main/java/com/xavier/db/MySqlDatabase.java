package com.xavier.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.xavier.config.TypeMapping;
import com.xavier.db.model.Table;

public class MySqlDatabase extends DefaultDatabase {

    private static final String TABLE_COMMENTS_SQL  = "show table status where NAME=?";

    public MySqlDatabase(Connection connection, TypeMapping typeMapping){
        super(connection, typeMapping);
    }

    @Override
    public Table getTable(String catalog, String schema, String tableName) throws SQLException {
        Table table = super.getTable(catalog, schema, tableName);
        if (table != null) {
            introspectTableComments(table, TABLE_COMMENTS_SQL, "COMMENT");
        }
        return table;
    }

}
