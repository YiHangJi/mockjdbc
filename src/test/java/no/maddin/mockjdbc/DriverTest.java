package no.maddin.mockjdbc;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.sql.*;
import java.util.Properties;

import com.ximalaya.xql.client.CommitParams;
import com.ximalaya.xql.client.XQLClient;
import com.ximalaya.xql.client.XQLResult;
import com.ximalaya.xql.client.XQLSchema;
import com.ximalaya.xql.client.exception.XQLClientException;
import com.ximalaya.xql.client.exception.XQLException;
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
//        ResultSet rs = connection.createStatement().executeQuery("select 2");
//        String sql = "select draft_order_id,payment_id,trade_order_no,relative_order_no,order_title,buyer_id,buyer_domain,consignee_id,consignee_domain,client_agent,client_ip,client_device_id,business_type_id,trade_type_id,order_type_id,order_version,order_from_id,order_source_type,order_source_value,execution_env_type,item_id,shop_id,quantity,unit_price,item_name,product_id,product_name,sku_id,spu_id,track_id,album_id,category_info,total_amount_edw,pay_amount_edw,income_amount_edw,discount_amount,discount_amount_edw,ship_amount_edw,xac_amount_edw,xac_fee_edw,channel_fee_edw,promotion_line_info,pay_channel_info,order_source_album_id,xmly_resource,pay_app_key,promotion_app_key,is_redeem_code_order,redeem_code,batch_id,share_type_id,protocol,notify_url,context,buyer_comment,seller_remark,create_timestamp,pay_timestamp,insert_timestamp,dt,category_id from dwd.trd_item_order_info_di where dt = 20201111 and category_id = 100 limit 1000";
        String sql = "select id,album_id,track_id,op_type_id,user_id,track_public_flag,track_create_timestamp,track_audit_status_code,track_transcode_state_code,track_delete_flag,track_visible_type,dt from dwd.track_record_detail_df limit 1000 ";
        ResultSet rs = connection.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            System.out.println(rs.getObject("track_visible_type"));
        }
    }

    @Test
    void append() {
        LogUtil.append("ccc");
    }

    @Test
    void testXQLCsv() throws XQLClientException {
        XQLClient client = null;
        XQLResult result = null;
        try {
            client = XQLClient.builder()
                    //访问线上 http://PROD_ENV_XQL，访问测试 http://TEST_ENV_XQL，访问UAT http://UAT_ENV_XQL
                    .server("http://PROD_ENV_XQL")
                    //用于提交任务的脚本token（xql验证邮件中有）
                    .token("tableau_jdbc_test#e15bcb2ec7bf84689fcab6175cd6b9a5")
                    .build();

            String xql = "load jdbc.`mysql6.unicorn_v2.static_data_model`  (id,name,remind_conf,is_deleted,dependency_wait_timeout_minute) where is_deleted = false as t;\n" +
                    "select id, name, remind_conf, get_json_object(remind_conf, \"$.isEnabled\") isEnabled from t limit 3";
            //description  形如 {"planId":1562,"taskId":13068,"taskName":"商业智能中心.tmp_report_user_remain_info_daily","system":"panda"},让我们能迅速找到相关用户
            CommitParams commitParams = new CommitParams.Builder().groupName("test").description("{}").build();
            result = client.executeXQL(xql, commitParams);

            //一定要调用next方法才可以开始读取数据，必不可少
            while (result.next()) {
                //通过索引获取字段值
                XQLSchema schema = result.getSchema();
                System.out.println(schema.fields().mkString(","));
                System.out.println(result.getRecordString());
            }
        } catch (XQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null)
                try {
                    result.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (client != null)
                client.close();
        }
    }
}
