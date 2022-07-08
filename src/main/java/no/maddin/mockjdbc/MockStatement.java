package no.maddin.mockjdbc;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class MockStatement implements PreparedStatement {

    private File currentFile = new File("/tmp",  "mock.csv");
    private Properties connectionProperties;
    private SQLWarning warnings;
    private MockResultSet currentResultSet;
    private AtomicBoolean isClosed = new AtomicBoolean(true);
    private String sql;

    MockStatement(Properties connectionProperties, String sql) throws SQLException {
        this.connectionProperties = connectionProperties;
        this.sql = sql;
//        if (sql != null) {
//            openCurrentFile(sql);
//        }
        if (sql != null) {
            currentResultSet = new XQLResultSet(sql, currentFile);
        }
    }

//    private synchronized void openCurrentFile(String sql) {
//        LogUtil.append(sql);
//
//        if (currentFile != null) {
//            throw new IllegalArgumentException("file already set");
//        }
//        currentFile = new File(connectionProperties.getProperty("path"),  "mock.csv");
//        isClosed.set(false);
//    }

//    @Override
//    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
//        throw new LogUnsupportedOperationException("registerOutParameter");
//
//    }
//
//    @Override
//    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
//        throw new LogUnsupportedOperationException("registerOutParameter");
//
//    }
//
//    @Override
//    public boolean wasNull() throws SQLException {
//        throw new LogUnsupportedOperationException("wasNull");
//
//    }
//
//    @Override
//    public String getString(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getString");
//
//    }
//
//    @Override
//    public boolean getBoolean(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getBoolean");
//
//    }
//
//    @Override
//    public byte getByte(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getByte");
//
//    }
//
//    @Override
//    public short getShort(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getShort");
//
//    }
//
//    @Override
//    public int getInt(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getInt");
//
//    }
//
//    @Override
//    public long getLong(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getLong");
//
//    }
//
//    @Override
//    public float getFloat(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getFloat");
//
//    }
//
//    @Override
//    public double getDouble(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getDouble");
//
//    }
//
//    @Override
//    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
//        throw new LogUnsupportedOperationException("getBigDecimal");
//
//    }
//
//    @Override
//    public byte[] getBytes(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getBytes");
//
//    }
//
//    @Override
//    public Date getDate(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getDate");
//
//    }
//
//    @Override
//    public Time getTime(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getTime");
//
//    }
//
//    @Override
//    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getTimestamp");
//
//    }
//
//    @Override
//    public Object getObject(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getObject");
//
//    }
//
//    @Override
//    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getBigDecimal");
//
//    }
//
//    @Override
//    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
//        throw new LogUnsupportedOperationException("getObject");
//
//    }
//
//    @Override
//    public Ref getRef(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getRef");
//
//    }
//
//    @Override
//    public Blob getBlob(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getBlob");
//
//    }
//
//    @Override
//    public Clob getClob(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getClob");
//
//    }
//
//    @Override
//    public Array getArray(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getArray");
//
//    }
//
//    @Override
//    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("getDate");
//
//    }
//
//    @Override
//    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("getTime");
//
//    }
//
//    @Override
//    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("getTimestamp");
//
//    }
//
//    @Override
//    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
//        throw new LogUnsupportedOperationException("registerOutParameter");
//
//    }
//
//    @Override
//    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
//        throw new LogUnsupportedOperationException("registerOutParameter");
//
//    }
//
//    @Override
//    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
//        throw new LogUnsupportedOperationException("registerOutParameter");
//
//    }
//
//    @Override
//    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
//        throw new LogUnsupportedOperationException("registerOutParameter");
//
//    }
//
//    @Override
//    public URL getURL(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getURL");
//
//    }
//
//    @Override
//    public void setURL(String parameterName, URL val) throws SQLException {
//        throw new LogUnsupportedOperationException("setURL");
//
//    }
//
//    @Override
//    public void setNull(String parameterName, int sqlType) throws SQLException {
//        throw new LogUnsupportedOperationException("setNull");
//
//    }
//
//    @Override
//    public void setBoolean(String parameterName, boolean x) throws SQLException {
//        throw new LogUnsupportedOperationException("setBoolean");
//
//    }
//
//    @Override
//    public void setByte(String parameterName, byte x) throws SQLException {
//        throw new LogUnsupportedOperationException("setByte");
//
//    }
//
//    @Override
//    public void setShort(String parameterName, short x) throws SQLException {
//        throw new LogUnsupportedOperationException("setShort");
//
//    }
//
//    @Override
//    public void setInt(String parameterName, int x) throws SQLException {
//        throw new LogUnsupportedOperationException("setInt");
//
//    }
//
//    @Override
//    public void setLong(String parameterName, long x) throws SQLException {
//        throw new LogUnsupportedOperationException("setLong");
//
//    }
//
//    @Override
//    public void setFloat(String parameterName, float x) throws SQLException {
//        throw new LogUnsupportedOperationException("setFloat");
//
//    }
//
//    @Override
//    public void setDouble(String parameterName, double x) throws SQLException {
//        throw new LogUnsupportedOperationException("setDouble");
//
//    }
//
//    @Override
//    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
//        throw new LogUnsupportedOperationException("setBigDecimal");
//
//    }
//
//    @Override
//    public void setString(String parameterName, String x) throws SQLException {
//        throw new LogUnsupportedOperationException("setString");
//
//    }
//
//    @Override
//    public void setBytes(String parameterName, byte[] x) throws SQLException {
//        throw new LogUnsupportedOperationException("setBytes");
//
//    }
//
//    @Override
//    public void setDate(String parameterName, Date x) throws SQLException {
//        throw new LogUnsupportedOperationException("setDate");
//
//    }
//
//    @Override
//    public void setTime(String parameterName, Time x) throws SQLException {
//        throw new LogUnsupportedOperationException("setTime");
//
//    }
//
//    @Override
//    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
//        throw new LogUnsupportedOperationException("setTimestamp");
//
//    }
//
//    @Override
//    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
//        throw new LogUnsupportedOperationException("setAsciiStream");
//
//    }
//
//    @Override
//    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
//        throw new LogUnsupportedOperationException("setBinaryStream");
//
//    }
//
//    @Override
//    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
//        throw new LogUnsupportedOperationException("setObject");
//
//    }
//
//    @Override
//    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
//        throw new LogUnsupportedOperationException("setObject");
//
//    }
//
//    @Override
//    public void setObject(String parameterName, Object x) throws SQLException {
//        throw new LogUnsupportedOperationException("setObject");
//
//    }
//
//    @Override
//    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
//        throw new LogUnsupportedOperationException("setCharacterStream");
//
//    }
//
//    @Override
//    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("setDate");
//
//    }
//
//    @Override
//    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("setTime");
//
//    }
//
//    @Override
//    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("setTimestamp");
//
//    }
//
//    @Override
//    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
//        throw new LogUnsupportedOperationException("setNull");
//
//    }
//
//    @Override
//    public String getString(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getString");
//
//    }
//
//    @Override
//    public boolean getBoolean(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getBoolean");
//
//    }
//
//    @Override
//    public byte getByte(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getByte");
//
//    }
//
//    @Override
//    public short getShort(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getShort");
//
//    }
//
//    @Override
//    public int getInt(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getInt");
//
//    }
//
//    @Override
//    public long getLong(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getLong");
//
//    }
//
//    @Override
//    public float getFloat(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getFloat");
//
//    }
//
//    @Override
//    public double getDouble(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getDouble");
//
//    }
//
//    @Override
//    public byte[] getBytes(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getBytes");
//
//    }
//
//    @Override
//    public Date getDate(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getDate");
//
//    }
//
//    @Override
//    public Time getTime(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getTime");
//
//    }
//
//    @Override
//    public Timestamp getTimestamp(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getTimestamp");
//
//    }
//
//    @Override
//    public Object getObject(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getObject");
//
//    }
//
//    @Override
//    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getBigDecimal");
//
//    }
//
//    @Override
//    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
//        throw new LogUnsupportedOperationException("getObject");
//
//    }
//
//    @Override
//    public Ref getRef(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getRef");
//
//    }
//
//    @Override
//    public Blob getBlob(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getBlob");
//
//    }
//
//    @Override
//    public Clob getClob(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getClob");
//
//    }
//
//    @Override
//    public Array getArray(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getArray");
//
//    }
//
//    @Override
//    public Date getDate(String parameterName, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("getDate");
//
//    }
//
//    @Override
//    public Time getTime(String parameterName, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("getTime");
//
//    }
//
//    @Override
//    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
//        throw new LogUnsupportedOperationException("getTimestamp");
//
//    }
//
//    @Override
//    public URL getURL(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getURL");
//
//    }
//
//    @Override
//    public RowId getRowId(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getRowId");
//
//    }
//
//    @Override
//    public RowId getRowId(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getRowId");
//
//    }
//
//    @Override
//    public void setRowId(String parameterName, RowId x) throws SQLException {
//        throw new LogUnsupportedOperationException("setRowId");
//
//    }
//
//    @Override
//    public void setNString(String parameterName, String value) throws SQLException {
//        throw new LogUnsupportedOperationException("setNString");
//
//    }
//
//    @Override
//    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setNCharacterStream");
//
//    }
//
//    @Override
//    public void setNClob(String parameterName, NClob value) throws SQLException {
//        throw new LogUnsupportedOperationException("setNClob");
//
//    }
//
//    @Override
//    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setClob");
//
//    }
//
//    @Override
//    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setBlob");
//
//    }
//
//    @Override
//    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setNClob");
//
//    }
//
//    @Override
//    public NClob getNClob(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getNClob");
//
//    }
//
//    @Override
//    public NClob getNClob(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getNClob");
//
//    }
//
//    @Override
//    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
//        throw new LogUnsupportedOperationException("setSQLXML");
//
//    }
//
//    @Override
//    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getSQLXML");
//
//    }
//
//    @Override
//    public SQLXML getSQLXML(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getSQLXML");
//
//    }
//
//    @Override
//    public String getNString(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getNString");
//
//    }
//
//    @Override
//    public String getNString(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getNString");
//
//    }
//
//    @Override
//    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getNCharacterStream");
//
//    }
//
//    @Override
//    public Reader getNCharacterStream(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getNCharacterStream");
//
//    }
//
//    @Override
//    public Reader getCharacterStream(int parameterIndex) throws SQLException {
//        throw new LogUnsupportedOperationException("getCharacterStream");
//
//    }
//
//    @Override
//    public Reader getCharacterStream(String parameterName) throws SQLException {
//        throw new LogUnsupportedOperationException("getCharacterStream");
//
//    }
//
//    @Override
//    public void setBlob(String parameterName, Blob x) throws SQLException {
//        throw new LogUnsupportedOperationException("setBlob");
//
//    }
//
//    @Override
//    public void setClob(String parameterName, Clob x) throws SQLException {
//        throw new LogUnsupportedOperationException("setClob");
//
//    }
//
//    @Override
//    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setAsciiStream");
//
//    }
//
//    @Override
//    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setBinaryStream");
//
//    }
//
//    @Override
//    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
//        throw new LogUnsupportedOperationException("setCharacterStream");
//
//    }
//
//    @Override
//    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
//        throw new LogUnsupportedOperationException("setAsciiStream");
//
//    }
//
//    @Override
//    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
//        throw new LogUnsupportedOperationException("setBinaryStream");
//
//    }
//
//    @Override
//    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
//        throw new LogUnsupportedOperationException("setCharacterStream");
//
//    }
//
//    @Override
//    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
//        throw new LogUnsupportedOperationException("setNCharacterStream");
//
//    }
//
//    @Override
//    public void setClob(String parameterName, Reader reader) throws SQLException {
//        throw new LogUnsupportedOperationException("setClob");
//
//    }
//
//    @Override
//    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
//        throw new LogUnsupportedOperationException("setBlob");
//
//    }
//
//    @Override
//    public void setNClob(String parameterName, Reader reader) throws SQLException {
//        throw new LogUnsupportedOperationException("setNClob");
//
//    }
//
//    @Override
//    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
//        throw new LogUnsupportedOperationException("getObject");
//
//    }
//
//    @Override
//    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
//        throw new LogUnsupportedOperationException("getObject");
//
//    }

    @Override
    public ResultSet executeQuery() throws SQLException {
//        return new MockResultSet(currentFile);
        LogUtil.append("executeQuery");
        return getResultSet();
    }

    @Override
    public int executeUpdate() throws SQLException {
        throw new LogUnsupportedOperationException("executeUpdate");

    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        throw new LogUnsupportedOperationException("setNull");
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        throw new LogUnsupportedOperationException("setBoolean");

    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        throw new LogUnsupportedOperationException("setByte");

    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        throw new LogUnsupportedOperationException("setShort");

    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        throw new LogUnsupportedOperationException("setInt");

    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        throw new LogUnsupportedOperationException("setLong");

    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        throw new LogUnsupportedOperationException("setFloat");

    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        throw new LogUnsupportedOperationException("setDouble");

    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        throw new LogUnsupportedOperationException("setBigDecimal");

    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        throw new LogUnsupportedOperationException("setString");

    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        throw new LogUnsupportedOperationException("setBytes");

    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        throw new LogUnsupportedOperationException("setDate");

    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        throw new LogUnsupportedOperationException("setTime");

    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        throw new LogUnsupportedOperationException("setTimestamp");

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("setAsciiStream");

    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("setUnicodeStream");

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("setBinaryStream");

    }

    @Override
    public void clearParameters() throws SQLException {
        throw new LogUnsupportedOperationException("clearParameters");

    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        throw new LogUnsupportedOperationException("setObject");

    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        throw new LogUnsupportedOperationException("setObject");

    }

    @Override
    public boolean execute() throws SQLException {

        LogUtil.append("execute: " + sql);
        // fixme
//        currentResultSet = new XQLResultSet(sql, currentFile);


//        if (currentFile.exists()) {
//            currentResultSet = new MockResultSet(currentFile);
//            return true;
//        } else {
//            currentResultSet = null;
//            return false;
//        }

        return true;
    }

    @Override
    public void addBatch() throws SQLException {
        throw new LogUnsupportedOperationException("addBatch");

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        throw new LogUnsupportedOperationException("setCharacterStream");

    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        throw new LogUnsupportedOperationException("setRef");

    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        throw new LogUnsupportedOperationException("setBlob");

    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        throw new LogUnsupportedOperationException("setClob");

    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        throw new LogUnsupportedOperationException("setArray");

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;

    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("setDate");

    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("setTime");

    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("setTimestamp");

    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        throw new LogUnsupportedOperationException("setNull");

    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        throw new LogUnsupportedOperationException("setURL");

    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new LogUnsupportedOperationException("getParameterMetaData");

    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw new LogUnsupportedOperationException("setRowId");

    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        throw new LogUnsupportedOperationException("setNString");

    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setNCharacterStream");

    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw new LogUnsupportedOperationException("setNClob");

    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setClob");

    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setBlob");

    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setNClob");

    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw new LogUnsupportedOperationException("setSQLXML");

    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        throw new LogUnsupportedOperationException("setObject");

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setAsciiStream");

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setBinaryStream");

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("setCharacterStream");

    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw new LogUnsupportedOperationException("setAsciiStream");

    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        throw new LogUnsupportedOperationException("setBinaryStream");

    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("setCharacterStream");

    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw new LogUnsupportedOperationException("setNCharacterStream");

    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("setClob");

    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw new LogUnsupportedOperationException("setBlob");

    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("setNClob");

    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
//        openCurrentFile(sql);
//        currentResultSet = new MockResultSet(currentFile);
        currentResultSet = new XQLResultSet(sql, currentFile);

        return currentResultSet;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        throw new LogUnsupportedOperationException("executeUpdate");

    }

    @Override
    public void close() throws SQLException {
        if (currentFile != null) {
            currentFile = null;
        }
        isClosed.set(true);

        currentResultSet.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxFieldSize");

    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        throw new LogUnsupportedOperationException("setMaxFieldSize");

    }

    @Override
    public int getMaxRows() throws SQLException {
        throw new LogUnsupportedOperationException("getMaxRows");

    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        throw new LogUnsupportedOperationException("setMaxRows");

    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new LogUnsupportedOperationException("setEscapeProcessing");

    }

    @Override
    public int getQueryTimeout() throws SQLException {
        throw new LogUnsupportedOperationException("getQueryTimeout");

    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        throw new LogUnsupportedOperationException("setQueryTimeout");

    }

    @Override
    public void cancel() throws SQLException {
        throw new LogUnsupportedOperationException("cancel");

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return warnings;
    }

    @Override
    public void clearWarnings() throws SQLException {
        warnings = null;

    }

    @Override
    public void setCursorName(String name) throws SQLException {
        throw new LogUnsupportedOperationException("setCursorName");

    }

    @Override
    public boolean execute(String sql) throws SQLException {
//        openCurrentFile(sql);
        currentResultSet = new XQLResultSet(sql, currentFile);

        return execute();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return currentResultSet;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        throw new LogUnsupportedOperationException("getUpdateCount");

    }

    @Override
    public boolean getMoreResults() throws SQLException {
        throw new LogUnsupportedOperationException("getMoreResults");

    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new LogUnsupportedOperationException("setFetchDirection");

    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new LogUnsupportedOperationException("getFetchDirection");

    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        // fixme
//        throw new LogUnsupportedOperationException("setFetchSize");

    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new LogUnsupportedOperationException("getFetchSize");

    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        throw new LogUnsupportedOperationException("getResultSetConcurrency");

    }

    @Override
    public int getResultSetType() throws SQLException {
        throw new LogUnsupportedOperationException("getResultSetType");

    }

    @Override
    public void addBatch(String sql) throws SQLException {
        throw new LogUnsupportedOperationException("addBatch");

    }

    @Override
    public void clearBatch() throws SQLException {
        throw new LogUnsupportedOperationException("clearBatch");

    }

    @Override
    public int[] executeBatch() throws SQLException {
        throw new LogUnsupportedOperationException("executeBatch");

    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new LogUnsupportedOperationException("getConnection");

    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        throw new LogUnsupportedOperationException("getMoreResults");

    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new LogUnsupportedOperationException("getGeneratedKeys");

    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new LogUnsupportedOperationException("executeUpdate");

    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new LogUnsupportedOperationException("executeUpdate");

    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new LogUnsupportedOperationException("executeUpdate");

    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return execute(sql);

    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return execute(sql);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return execute(sql);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new LogUnsupportedOperationException("getResultSetHoldability");

    }

    @Override
    public boolean isClosed() throws SQLException {
        return isClosed.get();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        throw new LogUnsupportedOperationException("setPoolable");

    }

    @Override
    public boolean isPoolable() throws SQLException {
        throw new LogUnsupportedOperationException("isPoolable");

    }

    @Override
    public void closeOnCompletion() throws SQLException {
        throw new LogUnsupportedOperationException("closeOnCompletion");

    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        throw new LogUnsupportedOperationException("isCloseOnCompletion");

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new LogUnsupportedOperationException("unwrap");

    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new LogUnsupportedOperationException("isWrapperFor");
    }
}
