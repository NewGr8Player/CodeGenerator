package com.xavier.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.xavier.config.TypeMapping;
import com.xavier.db.model.Table;

public class OracleDatabase extends DefaultDatabase {

    private static final String TABLE_COMMENTS_SQL  = "select * from user_tab_comments where TABLE_NAME=?";
    private static final String COLUMN_COMMENTS_SQL = "select * from user_col_comments where TABLE_NAME=?";

    public OracleDatabase(Connection connection, TypeMapping typeMapping){
        super(connection, typeMapping);
    }

    @Override
    public Table getTable(String catalog, String schema, String tableName) throws SQLException {
        Table table = super.getTable(catalog, schema, tableName);
        if (table != null) {
            introspectTableComments(table, TABLE_COMMENTS_SQL, "COMMENTS");
            introspectTableColumnsComments(table, COLUMN_COMMENTS_SQL, "COLUMN_NAME", "COMMENTS");
        }
        return table;
    }

}
