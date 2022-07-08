package no.maddin.mockjdbc;

import com.ximalaya.xql.client.XQLSchema;

import java.sql.SQLException;

/**
 * @author Han at 2022/7/7.
 * email:   lynn.jiang@ximalaya.com
 */
public class XQLResultSetMetaData extends JdbcMockResultSetMetaData {

    private XQLSchema xqlSchema;

    XQLResultSetMetaData(XQLSchema xqlSchema, String tableName, String headerLine) {
        super(tableName, headerLine);
        this.xqlSchema = xqlSchema;
    }

    @Override
    public int getColumnCount() throws SQLException {

        int size = xqlSchema.size();
        LogUtil.append("getColumnCount: " + size);

        return size;
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        String columnName = getColumnName(column);
        LogUtil.append("getColumnLabel: " + columnName);

        return columnName;
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        String name = xqlSchema.getFieldByIndex(column).name();
        LogUtil.append("getColumnName: " + name);

        return name;
    }

    @Override
    public int getColumnType(int column) throws SQLException {
        int dataType = xqlSchema.getFieldByIndex(column).dataType();
        LogUtil.append("getColumnType: " + dataType);

        return dataType;
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        String name = DataType.fromType(xqlSchema.getFieldByIndex(column).dataType()).name();
        LogUtil.append("getColumnTypeName: " + name);
        return name;
    }

    public String[] getColumnNames() {
        throw new LogUnsupportedOperationException("getColumnNames");
    }
}
