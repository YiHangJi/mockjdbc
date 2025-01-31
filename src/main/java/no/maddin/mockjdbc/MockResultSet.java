package no.maddin.mockjdbc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockResultSet implements ResultSet {
//    private final File csvFile;
    private final Iterator<String> lines;
    private Record currentRecord;
    private MockResultSetMetaData metaData;

    MockResultSet(File csvFile) throws SQLException {
//        this.csvFile = csvFile;
        try (Stream<String> stringStream = Files.lines(csvFile.toPath())) {
            this.lines = stringStream.collect(Collectors.toList()).iterator();
        } catch (IOException ex) {
            throw new SQLException(ex);
        }
        metaData = createMetaData();
    }

    MockResultSet(Iterator<String> lines) throws SQLException {
        this.lines = lines;
        metaData = createMetaData();
    }

    private MockResultSetMetaData createMetaData() throws SQLException {
        if (!lines.hasNext()) {
            throw new SQLException("Missing header line, cannot create metadata");
        }
        return new JdbcMockResultSetMetaData("mytable", lines.next());
    }

    private String[] splitLine(String s) {
        return s.split("\\s*,\\s*");
    }

    @Override
    public boolean next() throws SQLException {
        boolean hasNext = lines.hasNext();
        if (hasNext) {
            currentRecord = readRecord(lines.next());
        }
        return hasNext;
    }

    private Record readRecord(String line) {
        String[] stringRecord = splitLine(line);
        return Record.create(stringRecord, metaData.getColumnNames());
    }

    @Override
    public void close() throws SQLException {
        this.currentRecord = null;
        this.metaData = null;
    }

    @Override
    public boolean wasNull() throws SQLException {
        // fixme
//        if (lastReadColumn == 0) {
//            throw new IllegalStateException("You should get something before check nullability");
//        }
//        return getValue(lastReadColumn).isNull();
        return false;
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return check(currentRecord).get(metaData.getColumnName(columnIndex));
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getBoolean");
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        return getByte(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        return getShort(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        return getInt(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        return getLong(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        return getFloat(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        return getDouble(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        throw new LogUnsupportedOperationException("getBigDecimal");
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getBytes");
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        return getDate(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        return getTime(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return getTimestamp(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getAsciiStream");

    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getUnicodeStream");

    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getBinaryStream");

    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        String result = check(currentRecord).get(columnLabel);
        if (result == null) {
            throw new SQLException("No Result for column: " + columnLabel);
        }
        return result;
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getBoolean");

    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return Byte.parseByte(val);
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return Short.parseShort(val);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return Integer.parseInt(val);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return Long.parseLong(val);
    }

    private Record check(Record record) throws SQLException {
        if (record == null) {
            throw new SQLException("No current record");
        }
        return record;
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return Float.parseFloat(val);
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return Double.parseDouble(val);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        throw new LogUnsupportedOperationException("getBigDecimal");

    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getBytes");

    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        LocalDate ld = LocalDate.parse(val, DateTimeFormatter.ISO_DATE);
        return Date.valueOf(ld);
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        LocalTime lt = LocalTime.parse(val, DateTimeFormatter.ISO_TIME);
        return Time.valueOf(lt);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        LocalDateTime ldt = LocalDateTime.parse(val, DateTimeFormatter.ISO_DATE_TIME);
        return Timestamp.valueOf(ldt);
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getAsciiStream");

    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getUnicodeStream");

    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getBinaryStream");

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new LogUnsupportedOperationException("getWarnings");

    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new LogUnsupportedOperationException("clearWarnings");

    }

    @Override
    public String getCursorName() throws SQLException {
        throw new LogUnsupportedOperationException("getCursorName");

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        if (metaData == null) {
            throw new SQLException("ResultSet closed");
        }
        return metaData;
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getObject");

    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getObject");

    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("findColumn");

    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getCharacterStream");

    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getCharacterStream");

    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return getBigDecimal(metaData.getColumnLabel(columnIndex));
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        String val = check(currentRecord).get(columnLabel);
        return BigDecimal.valueOf(Double.parseDouble(val));
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        throw new LogUnsupportedOperationException("isBeforeFirst");

    }

    @Override
    public boolean isAfterLast() throws SQLException {
        throw new LogUnsupportedOperationException("isAfterLast");

    }

    @Override
    public boolean isFirst() throws SQLException {
        throw new LogUnsupportedOperationException("isFirst");

    }

    @Override
    public boolean isLast() throws SQLException {
        throw new LogUnsupportedOperationException("isLast");

    }

    @Override
    public void beforeFirst() throws SQLException {
        throw new LogUnsupportedOperationException("beforeFirst");

    }

    @Override
    public void afterLast() throws SQLException {
        throw new LogUnsupportedOperationException("afterLast");

    }

    @Override
    public boolean first() throws SQLException {
        throw new LogUnsupportedOperationException("first");

    }

    @Override
    public boolean last() throws SQLException {
        throw new LogUnsupportedOperationException("last");

    }

    @Override
    public int getRow() throws SQLException {
        throw new LogUnsupportedOperationException("getRow");

    }

    @Override
    public boolean absolute(int row) throws SQLException {
        throw new LogUnsupportedOperationException("absolute");

    }

    @Override
    public boolean relative(int rows) throws SQLException {
        throw new LogUnsupportedOperationException("relative");

    }

    @Override
    public boolean previous() throws SQLException {
        throw new LogUnsupportedOperationException("previous");

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
        //fixme
//        throw new LogUnsupportedOperationException("setFetchSize");

    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new LogUnsupportedOperationException("getFetchSize");

    }

    @Override
    public int getType() throws SQLException {
        throw new LogUnsupportedOperationException("getType");

    }

    @Override
    public int getConcurrency() throws SQLException {
        throw new LogUnsupportedOperationException("getConcurrency");

    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new LogUnsupportedOperationException("rowUpdated");

    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new LogUnsupportedOperationException("rowInserted");

    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new LogUnsupportedOperationException("rowDeleted");

    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("updateNull");

    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBoolean");

    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new LogUnsupportedOperationException("updateByte");

    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new LogUnsupportedOperationException("updateShort");

    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new LogUnsupportedOperationException("updateInt");

    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new LogUnsupportedOperationException("updateLong");

    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new LogUnsupportedOperationException("updateFloat");

    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new LogUnsupportedOperationException("updateDouble");

    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBigDecimal");

    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        throw new LogUnsupportedOperationException("updateString");

    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBytes");

    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new LogUnsupportedOperationException("updateDate");

    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new LogUnsupportedOperationException("updateTime");

    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        throw new LogUnsupportedOperationException("updateTimestamp");

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("updateAsciiStream");

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("updateBinaryStream");

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("updateCharacterStream");

    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        throw new LogUnsupportedOperationException("updateObject");

    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new LogUnsupportedOperationException("updateObject");

    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("updateNull");

    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBoolean");

    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        throw new LogUnsupportedOperationException("updateByte");

    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        throw new LogUnsupportedOperationException("updateShort");

    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        throw new LogUnsupportedOperationException("updateInt");

    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        throw new LogUnsupportedOperationException("updateLong");

    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new LogUnsupportedOperationException("updateFloat");

    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        throw new LogUnsupportedOperationException("updateDouble");

    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBigDecimal");

    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        throw new LogUnsupportedOperationException("updateString");

    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBytes");

    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        throw new LogUnsupportedOperationException("updateDate");

    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        throw new LogUnsupportedOperationException("updateTime");

    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        throw new LogUnsupportedOperationException("updateTimestamp");

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("updateAsciiStream");

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new LogUnsupportedOperationException("updateBinaryStream");

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        throw new LogUnsupportedOperationException("updateCharacterStream");

    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        throw new LogUnsupportedOperationException("updateObject");

    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        throw new LogUnsupportedOperationException("updateObject");

    }

    @Override
    public void insertRow() throws SQLException {
        throw new LogUnsupportedOperationException("insertRow");

    }

    @Override
    public void updateRow() throws SQLException {
        throw new LogUnsupportedOperationException("updateRow");

    }

    @Override
    public void deleteRow() throws SQLException {
        throw new LogUnsupportedOperationException("deleteRow");

    }

    @Override
    public void refreshRow() throws SQLException {
        throw new LogUnsupportedOperationException("refreshRow");

    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new LogUnsupportedOperationException("cancelRowUpdates");

    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new LogUnsupportedOperationException("moveToInsertRow");

    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new LogUnsupportedOperationException("moveToCurrentRow");

    }

    @Override
    public Statement getStatement() throws SQLException {
        throw new LogUnsupportedOperationException("getStatement");

    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        throw new LogUnsupportedOperationException("getObject");

    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getRef");

    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getBlob");

    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getClob");

    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getArray");

    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        throw new LogUnsupportedOperationException("getObject");

    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getRef");

    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getBlob");

    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getClob");

    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getArray");

    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("getDate");

    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("getDate");

    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("getTime");

    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("getTime");

    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("getTimestamp");

    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        throw new LogUnsupportedOperationException("getTimestamp");

    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getURL");

    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getURL");

    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new LogUnsupportedOperationException("updateRef");

    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        throw new LogUnsupportedOperationException("updateRef");

    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBlob");

    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBlob");

    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new LogUnsupportedOperationException("updateClob");

    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        throw new LogUnsupportedOperationException("updateClob");

    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new LogUnsupportedOperationException("updateArray");

    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        throw new LogUnsupportedOperationException("updateArray");

    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getRowId");

    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getRowId");

    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new LogUnsupportedOperationException("updateRowId");

    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new LogUnsupportedOperationException("updateRowId");

    }

    @Override
    public int getHoldability() throws SQLException {
        throw new LogUnsupportedOperationException("getHoldability");

    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new LogUnsupportedOperationException("isClosed");

    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw new LogUnsupportedOperationException("updateNString");

    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw new LogUnsupportedOperationException("updateNString");

    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new LogUnsupportedOperationException("updateNClob");

    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw new LogUnsupportedOperationException("updateNClob");

    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getNClob");

    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getNClob");

    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getSQLXML");

    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getSQLXML");

    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new LogUnsupportedOperationException("updateSQLXML");

    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw new LogUnsupportedOperationException("updateSQLXML");

    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getNString");

    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getNString");

    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new LogUnsupportedOperationException("getNCharacterStream");

    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new LogUnsupportedOperationException("getNCharacterStream");

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateNCharacterStream");

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateNCharacterStream");

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateAsciiStream");

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateBinaryStream");

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateCharacterStream");

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateAsciiStream");

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateBinaryStream");

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateCharacterStream");

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateBlob");

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateBlob");

    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateClob");

    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateClob");

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateNClob");

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new LogUnsupportedOperationException("updateNClob");

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new LogUnsupportedOperationException("updateNCharacterStream");

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("updateNCharacterStream");

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw new LogUnsupportedOperationException("updateAsciiStream");

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBinaryStream");

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new LogUnsupportedOperationException("updateCharacterStream");

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw new LogUnsupportedOperationException("updateAsciiStream");

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw new LogUnsupportedOperationException("updateBinaryStream");

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("updateCharacterStream");

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw new LogUnsupportedOperationException("updateBlob");

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw new LogUnsupportedOperationException("updateBlob");

    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("updateClob");

    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("updateClob");

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("updateNClob");

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw new LogUnsupportedOperationException("updateNClob");

    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new LogUnsupportedOperationException("getObject");

    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new LogUnsupportedOperationException("getObject");

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new LogUnsupportedOperationException("unwrap");

    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new LogUnsupportedOperationException("isWrapperFor");

    }

    private static class Record extends java.util.LinkedHashMap<String, String> {

        private Record() {
        }

        public static Record create(String[] rawData, String[] headers) {
            int col = 0;
            Record record = new Record();
            for (String header : headers) {
                record.put(header, rawData[col++]);
            }

            return record;
        }
    }

}
