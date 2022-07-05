package no.maddin.mockjdbc;

import java.sql.SQLException;

/**
 * @author Han at 2022/7/5.
 * email:   lynn.jiang@ximalaya.com
 */
public class JdbcMockResultSetMetaData extends MockResultSetMetaData {

    JdbcMockResultSetMetaData(String tableName, String headerLine) {
        super(tableName, headerLine);
    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        return 80;
    }

    @Override
    public int isNullable(int column) throws SQLException {
//        // fixme
//        return getCol(column).isNullable()
//                ? columnNullable
//                : columnNoNulls;
        return columnNullable;
    }
}
