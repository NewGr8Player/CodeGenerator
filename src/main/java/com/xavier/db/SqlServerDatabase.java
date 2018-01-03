package com.xavier.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.xavier.config.TypeMapping;
import com.xavier.db.model.Table;

public class SqlServerDatabase extends DefaultDatabase {

    private static final String TABLE_COMMENTS_SQL  = "SELECT a.NAME,CAST (isnull(e.[value], '') AS nvarchar (100)) AS REMARK FROM sys.tables a INNER JOIN sys.objects c ON a.object_id = c.object_id LEFT JOIN sys.extended_properties e ON e.major_id = c.object_id AND e.minor_id = 0 AND e.class = 1 where c.name=?";
    private static final String COLUMN_COMMENTS_SQL = "select a.NAME,cast(isnull(e.[value],'') as nvarchar(100)) as REMARK from sys.columns a inner join sys.objects c on a.object_id=c.object_id and c.type='u' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name=?";

    public SqlServerDatabase(Connection connection, TypeMapping typeMapping){
        super(connection, typeMapping);
    }

    @Override
    public Table getTable(String catalog, String schema, String tableName) throws SQLException {
        Table table = super.getTable(catalog, schema, tableName);
        if (table != null) {
            introspectTableComments(table, TABLE_COMMENTS_SQL, "REMARK");
            introspectTableColumnsComments(table, COLUMN_COMMENTS_SQL, "NAME", "REMARK");
        }
        return table;
    }

}
