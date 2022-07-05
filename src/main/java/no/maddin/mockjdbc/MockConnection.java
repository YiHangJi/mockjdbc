package no.maddin.mockjdbc;

import java.sql.*;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MockConnection implements Connection {
    private static final Properties EMPTY_PROPERTIES = new Properties();
    private final Properties connectionProperties = new Properties();
    private final String url;
    private Statement currentStatement;
    private final MockDatabaseMetaData metadata;

    public MockConnection(String url, Properties info) {
        this.url = url;
        addPropsFromUrl(url);
        this.connectionProperties.putAll(info);
        this.metadata = new MockDatabaseMetaData();
        metadata.setURL(url);
    }

    private void addPropsFromUrl(String url) {
        int propSeparator = url.indexOf(';');
        String[] propPairs = url.substring(propSeparator + 1).split("\\s*,\\s*");

        for (String propPair : propPairs) {
            String[] keyValue = propPair.split("\\s*=\\s*");
            connectionProperties.put(keyValue[0], keyValue[1]);
        }
    }

    @Override
    public Statement createStatement() throws SQLException {
        return createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        throw new UnsupportedOperationException("prepareCall(String)");
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        throw new UnsupportedOperationException("nativeSQL(String)");
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        // do nothing
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        throw new UnsupportedOperationException("getAutoCommit()");
    }

    @Override
    public void commit() throws SQLException {
        throw new UnsupportedOperationException("commit()");
    }

    @Override
    public void rollback() throws SQLException {
        throw new UnsupportedOperationException("rollback()");
    }

    @Override
    public void close() throws SQLException {
        if (currentStatement != null) {
            currentStatement.close();
            currentStatement = null;
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        return currentStatement == null || currentStatement.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return metadata;
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        throw new UnsupportedOperationException("setReadOnly");
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return true;
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        throw new UnsupportedOperationException("setCatalog(String)");
    }

    @Override
    public String getCatalog() throws SQLException {
        throw new UnsupportedOperationException("getCatalog()");
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        throw new UnsupportedOperationException("steTransactionIsolation(int)");
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        throw new UnsupportedOperationException("getTransactionIsolation");
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException("getWarnings");
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException("clearWarnings");
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return createStatement(resultSetType, resultSetConcurrency, ResultSet.CLOSE_CURSORS_AT_COMMIT);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return prepareStatement(sql, resultSetType, resultSetConcurrency, ResultSet.CLOSE_CURSORS_AT_COMMIT);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("prepareCall(String,int,int)");
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return Collections.emptyMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("setTypeMap(Map<String,Class<?>>)");
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        if (resultSetType != ResultSet.TYPE_FORWARD_ONLY) {
            throw new SQLException("Unsupported resultSetType " + resultSetType);
        }
        if (resultSetConcurrency != ResultSet.CONCUR_READ_ONLY) {
            throw new SQLException("Unsupported resultSetConcurrency " + resultSetConcurrency);
        }
        if (resultSetHoldability != ResultSet.CLOSE_CURSORS_AT_COMMIT) {
            throw new SQLException("Unsupported resultSetHoldability " + resultSetHoldability);
        }
        if (currentStatement != null && !currentStatement.isClosed()) {
            throw new SQLException("previous statement not closed");
        }
        currentStatement = new MockStatement(connectionProperties, null);
        return currentStatement;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        if (resultSetType != ResultSet.TYPE_FORWARD_ONLY) {
            throw new SQLException("Unsupported resultSetType " + resultSetType);
        }
        if (resultSetConcurrency != ResultSet.CONCUR_READ_ONLY) {
            throw new SQLException("Unsupported resultSetConcurrency " + resultSetConcurrency);
        }
        if (resultSetHoldability != ResultSet.CLOSE_CURSORS_AT_COMMIT) {
            throw new SQLException("Unsupported resultSetHoldability " + resultSetHoldability);
        }
        if (currentStatement != null && !currentStatement.isClosed()) {
            throw new SQLException("previous connection not closed");
        }
        currentStatement = new MockStatement(connectionProperties, sql);

        return (PreparedStatement)currentStatement;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("prepareStatement(String, int[])");
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("prepare(String,String[])");
    }

    @Override
    public Clob createClob() throws SQLException {
        throw new UnsupportedOperationException("createClob()");
    }

    @Override
    public Blob createBlob() throws SQLException {
        throw new UnsupportedOperationException("createBlob()");
    }

    @Override
    public NClob createNClob() throws SQLException {
        throw new UnsupportedOperationException("createNClob()");
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        throw new UnsupportedOperationException("createSQLXML()");
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        throw new UnsupportedOperationException("isValid()");
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return EMPTY_PROPERTIES;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public String getSchema() throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("not yet");
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

}
