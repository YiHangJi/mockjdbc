package no.maddin.mockjdbc;

import com.ximalaya.xql.client.CommitParams;
import com.ximalaya.xql.client.XQLClient;
import com.ximalaya.xql.client.XQLEmptyResultSet;
import com.ximalaya.xql.client.XQLResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @author Han at 2022/7/7.
 * email:   lynn.jiang@ximalaya.com
 */
public class XQLResultSet extends MockResultSet implements ResultSet {


    private static final Logger logger = LoggerFactory.getLogger(XQLResultSet.class);

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private XQLResult result;

    XQLResultSet(String xql, File csvFile) throws SQLException {
        super(csvFile);
        LogUtil.append(xql);

        logger.info("execute xql: {}", xql);

        if (xql.startsWith("DROP") || xql.startsWith("CREATE") || xql.startsWith("SELECT `SUBCOL`")) {
            result = new XQLEmptyResultSet(0L, null);
        } else {


            XQLClient client = XQLClient.builder()
                    //访问线上 http://PROD_ENV_XQL，访问测试 http://TEST_ENV_XQL，访问UAT http://UAT_ENV_XQL
                    .server("http://TEST_ENV_XQL")
                    //用于提交任务的脚本token（xql验证邮件中有）
//                    .token("tableau_jdbc_test#e15bcb2ec7bf84689fcab6175cd6b9a5")
                    .token("test_account#65a1be07c41e327d0535b563a7980b5f")
                    .build();

            CommitParams commitParams = new CommitParams.Builder().groupName("test").description("{}").build();

            try {
                result = client.executeXQL(xql, commitParams);

                LogUtil.append("execute xql finish " + result.getResultCount());

            } catch (Throwable e) {
                ByteArrayOutputStream io = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(io));
                try {
                    LogUtil.append(io.toString("UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() throws SQLException {
       if (result != null) {
           super.close();
           try {
               result.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }

    @Override
    public boolean next() throws SQLException {
        return result.next();
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return result.getString(columnIndex);
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return !"".equals(res) && Boolean.parseBoolean(res);
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        return result.getBytes(columnIndex)[0];
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? 0 : Short.parseShort(res);
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? 0 : Integer.parseInt(res);        }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? 0 : Long.parseLong(res);
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? 0 : Float.parseFloat(res);        }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? 0 : Double.parseDouble(res);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? null : new BigDecimal(res);
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        return result.getBytes(columnIndex);
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        Object res = result.get(columnIndex);
        if (res instanceof String) {
            return java.sql.Date.valueOf(LocalDate.parse(res.toString(), dateFormatter));
        } else if (res instanceof LocalDate) {
            return java.sql.Date.valueOf((LocalDate) res);
        } else {
            return (Date) res;
        }
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        return java.sql.Time.valueOf(result.getTime(columnIndex).toLocalTime());
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        Object res = result.get(columnIndex);
        if (res instanceof String) {
            return Timestamp.valueOf(LocalDateTime.parse(res.toString()));
        } else if (res instanceof LocalDateTime) {
            return Timestamp.valueOf((LocalDateTime) res);
        } else {
            return (Timestamp) res;
        }
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return result.getString(columnLabel);
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return !"".equals(res) && Boolean.parseBoolean(res);
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        return result.getBytes(columnLabel)[0];
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? 0 : Short.parseShort(res);         }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? 0 : Integer.parseInt(res);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? 0 : Long.parseLong(res);
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? 0 : Float.parseFloat(res);      }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? 0 : Double.parseDouble(res);        }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? null : new BigDecimal(res).setScale(scale);       }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        return result.getBytes(columnLabel);
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        Object res = result.get(columnLabel);
        if (res instanceof String) {
            return java.sql.Date.valueOf(LocalDate.parse(res.toString(), dateFormatter));
        } else if (res instanceof LocalDate) {
            return java.sql.Date.valueOf((LocalDate) res);
        } else {
            return (Date) res;
        }
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        return java.sql.Time.valueOf(result.getTime(columnLabel).toLocalTime());
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        Object res = result.get(columnLabel);
        if (res instanceof String) {
            return Timestamp.valueOf(LocalDateTime.parse(res.toString()));
        } else if (res instanceof LocalDateTime) {
            return Timestamp.valueOf((LocalDateTime) res);
        } else {
            return (Timestamp) res;
        }
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        String res = result.get(columnIndex).toString();

        return ("".equals(res)) ? null : new BigDecimal(res);       }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        String res = result.get(columnLabel).toString();

        return ("".equals(res)) ? null : new BigDecimal(res);    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new XQLResultSetMetaData(result.getSchema(), "mytable", "default|varchar");
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        Object res = result.get(columnIndex);
        if (res instanceof String) {
            return java.sql.Date.valueOf(LocalDate.parse(res.toString(), dateFormatter));
        } else if (res instanceof LocalDate) {
            return java.sql.Date.valueOf((LocalDate) res);
        } else {
            return (Date) res;
        }
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        Object res = result.get(columnLabel);
        if (res instanceof String) {
            return java.sql.Date.valueOf(LocalDate.parse(res.toString(), dateFormatter));
        } else if (res instanceof LocalDate) {
            return java.sql.Date.valueOf((LocalDate) res);
        } else {
            return (Date) res;
        }
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        Object res = result.get(columnIndex);
        if (res instanceof String) {
            return Timestamp.valueOf(LocalDateTime.parse(res.toString()));
        } else if (res instanceof LocalDateTime) {
            return Timestamp.valueOf((LocalDateTime) res);
        } else {
            return (Timestamp) res;
        }
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        Object res = result.get(columnLabel);
        if (res instanceof String) {
            return Timestamp.valueOf(LocalDateTime.parse(res.toString()));
        } else if (res instanceof LocalDateTime) {
            return Timestamp.valueOf((LocalDateTime) res);
        } else {
            return (Timestamp) res;
        }
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {

        int dataType = result.getSchema().getFieldByIndex(columnIndex).dataType();
        Object value = null;
        switch (MockResultSetMetaData.DataType.fromType(dataType)) {
            case VARCHAR:
                value = getString(columnIndex);
                break;
            case DATE:
                value = getDate(columnIndex);
                break;
            case REAL:
            case FLOAT:
                value = getFloat(columnIndex);
                break;
            case DOUBLE:
                value = getDouble(columnIndex);
                break;
            case TINYINT:
                value = getBoolean(columnIndex);
                break;
            case SMALLINT:
            case INTEGER:
                value = getInt(columnIndex);
                break;
            case BIGINT:
                value = getLong(columnIndex);
                break;
            case DECIMAL:
                value = getBigDecimal(columnIndex);
                break;
            case TIMESTAMP:
                value = getTimestamp(columnIndex);
                break;
            case TIME:
                value = getTime(columnIndex);
                break;
            default:
                throw new UnsupportedOperationException("不支持 dataType " + dataType);
        }
        return value;
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException  {
        int dataType = result.getSchema().getFieldByColumnName(columnLabel).dataType();
        Object value = null;
        switch (MockResultSetMetaData.DataType.fromType(dataType)) {
            case VARCHAR:
                value = getString(columnLabel);
                break;
            case DATE:
                value = getDate(columnLabel);
                break;
            case REAL:
            case FLOAT:
                value = getFloat(columnLabel);
                break;
            case DOUBLE:
                value = getDouble(columnLabel);
                break;
            case TINYINT:
                value = getBoolean(columnLabel);
                break;
            case SMALLINT:
            case INTEGER:
                value = getInt(columnLabel);
                break;
            case BIGINT:
                value = getLong(columnLabel);
                break;
            case DECIMAL:
                value = getBigDecimal(columnLabel);
                break;
            case TIMESTAMP:
                value = getTimestamp(columnLabel);
                break;
            case TIME:
                value = getTime(columnLabel);
                break;
            default:
                throw new UnsupportedOperationException("不支持 dataType " + dataType);
        }
        return value;
    }
}
