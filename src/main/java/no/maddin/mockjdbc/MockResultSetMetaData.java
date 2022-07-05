package no.maddin.mockjdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

class MockResultSetMetaData implements ResultSetMetaData {

    private enum DataType {
        VARCHAR(Types.VARCHAR),
        INTEGER(Types.INTEGER),
        DOUBLE(Types.DOUBLE),
        DATE(Types.DATE),
        TIME(Types.TIME),
        TIMESTAMP(Types.TIMESTAMP);

        private final int sqlType;

        DataType(int sqlType) {
            this.sqlType = sqlType;
        }

        public static DataType fromString(String spec) {
            return valueOf(spec.toUpperCase());
        }
    }

    private static final int DEFAULT_COLUMN_PRECISION = 60;
    private static final int DEFAULT_COLUMN_SCALE = 0;
    private final String tableName;
    private final String[] columnNames;
    private final LinkedHashMap<String, DataType> columnTypes = new LinkedHashMap<>();

    MockResultSetMetaData(String tableName, String headerLine) {
        String[] columnSpecs = headerLine.split("\\s*,\\s*");
        for (String columnSpec : columnSpecs) {
            String[] specElems = Objects.requireNonNull(columnSpec, "Header must be specified").split("\\s*\\|\\s*");
            DataType dt;
            switch(specElems.length) {
                case 1:
                    dt = DataType.VARCHAR;
                    break;
                case 2:
                    dt = DataType.fromString(specElems[1]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown column spec: " + columnSpec);
            }
            columnTypes.put(specElems[0], dt);
        }
        columnNames = columnTypes.keySet().toArray(new String[0]);
        this.tableName = tableName;
    }

    @Override
    public int getColumnCount() throws SQLException {
        return columnTypes.size();
    }

    @Override
    public boolean isAutoIncrement(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isAutoIncrement");

    }

    @Override
    public boolean isCaseSensitive(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isCaseSensitive");

    }

    @Override
    public boolean isSearchable(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isSearchable");

    }

    @Override
    public boolean isCurrency(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isCurrency");

    }

    @Override
    public int isNullable(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isNullable");

    }

    @Override
    public boolean isSigned(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isSigned");

    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        throw new LogUnsupportedOperationException("getColumnDisplaySize");

    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        return getColumnName(column);
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        return columnNames[column-1];
    }

    @Override
    public String getSchemaName(int column) throws SQLException {
        return "";
    }

    @Override
    public int getPrecision(int column) throws SQLException {
        return DEFAULT_COLUMN_PRECISION;
    }

    @Override
    public int getScale(int column) throws SQLException {
        return DEFAULT_COLUMN_SCALE;
    }

    @Override
    public String getTableName(int column) throws SQLException {
        return tableName;
    }

    @Override
    public String getCatalogName(int column) throws SQLException {
        throw new LogUnsupportedOperationException("getCatalogName");

    }

    @Override
    public int getColumnType(int column) throws SQLException {
        return columnTypes.get(columnNames[column-1]).sqlType;
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        return columnTypes.get(columnNames[column-1]).name();
    }

    @Override
    public boolean isReadOnly(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isReadOnly");

    }

    @Override
    public boolean isWritable(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isWritable");

    }

    @Override
    public boolean isDefinitelyWritable(int column) throws SQLException {
        throw new LogUnsupportedOperationException("isDefinitelyWritable");

    }

    @Override
    public String getColumnClassName(int column) throws SQLException {
        throw new LogUnsupportedOperationException("getColumnClassName");

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new LogUnsupportedOperationException("unwrap");

    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new LogUnsupportedOperationException("isWrapperFor");

    }

    public String[] getColumnNames() {
        return columnNames;
    }
}
