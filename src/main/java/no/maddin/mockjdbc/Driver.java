package no.maddin.mockjdbc;

import com.ximalaya.scala.common.json.JsonUtils;
import com.ximalaya.xql.api.model.JobCommitRequest;
import scala.collection.immutable.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Driver implements java.sql.Driver {


    static {

        try {
            JobCommitRequest jobCommitRequest = new JobCommitRequest("select 1");
            JsonUtils.toJson(jobCommitRequest);
        }  catch (Throwable e) {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(io));
            try {
                LogUtil.append(io.toString("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            PrintWriter pw = DriverManager.getLogWriter();
            if (pw != null) {
                e.printStackTrace(pw);
            } else {
                e.printStackTrace();
            }
        }
    }

    public Connection connect(String url, Properties info) throws SQLException {
        if (!acceptsURL(url)) {
            throw new SQLException(url + " not accepted by " + this.getClass().getName());
        }
        return new MockConnection(url, info);
    }

    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith("jdbc:mock:");
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return 1;
    }

    public int getMinorVersion() {
        return 0;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

}
