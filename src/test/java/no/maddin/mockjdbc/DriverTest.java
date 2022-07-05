package no.maddin.mockjdbc;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.sql.*;
import java.util.Properties;

import org.junit.jupiter.api.Test;


class DriverTest {

    @Test
    void loadDriverWithURL() throws Exception {
        java.sql.Driver drv = DriverManager.getDriver("jdbc:mock:csv;path=.");
        assertThat(drv, is(instanceOf(no.maddin.mockjdbc.Driver.class)));
    }

    @Test
    void driverConnection() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mock:csv;path=.");
        assertThat(connection, is(instanceOf(MockConnection.class)));
    }

    @Test
    void driverConnectionWithProperties() throws Exception {
        Properties props = new Properties();
        props.put("repeat", "2100");
        Connection connection = DriverManager.getConnection("jdbc:mock:csv;path=.", props);
        assertThat(connection, is(instanceOf(MockConnection.class)));
        assertThat(((MockConnection)connection).getConnectionProperties(), hasKey("repeat"));
    }

    @Test
    void driverConnectionWithOtherUrl() throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl")) {
            assertThat(con, is(notNullValue()));
        } catch (Exception ex) {
            assertThat(ex, hasProperty("message", containsString("not accepted")));
        }
    }

    @Test
    void showTables() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mock:csv;path=/tmp");

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(connection.getCatalog(), null, "TAB_%", null);
        while (rs.next()) {
            System.out.println(rs.getString(3));
        }
    }

    @Test
    void getFileName() throws Exception {
        String s = DriverTool.fileName("SELECT TOP 10000 `TableauSQL`.`a` AS `a`,\n" +
                "  `TableauSQL`.`b` AS `b`\n" +
                "FROM (\n" +
                "  select a, b from mytable\n" +
                ") `TableauSQL`");
        System.out.println(s);
    }

    @Test
    void getData() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mock:csv;path=/tmp");
//        ResultSet rs = connection.createStatement().executeQuery("select a, b from mytable");
        ResultSet rs = connection.prepareStatement("select a, b from `default`.`mytable`").executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    @Test
    void append() {
        LogUtil.append("ccc");
    }
}
