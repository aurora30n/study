package study.hbase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author Jiguang Wang
 * @since 2021/3/16 16:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HbaseDemoApplicationTests {

    @Autowired
    private HBaseClient hBaseClient;

   /* *//**
     * 测试删除、创建表
     *//*
    @Test
    public void testGetValue() {
        String value = hBaseClient.getValue("tbl_user", "mengday", "info", "age");
        System.out.println(value);
    }

    @Test
    public void testCreateTable() throws IOException {
        String tableName = "tbl_abc";
        hBaseClient.deleteTable(tableName);
        hBaseClient.createTable(tableName, new String[] {"cf1", "cf2"});
    }

    @Test
    public void dropTable() throws IOException {
        hBaseClient.deleteTable("tbl_abc");
    }


    @Test
    public void testInsertOrUpdate() throws IOException {
        hBaseClient.insertOrUpdate("tbl_abc", "rowKey1", "cf1", new String[]{"c1", "c2"}, new String[]{"v1", "v22"});
    }

    @Test
    public void testSelectOneRow() throws IOException {
        hBaseClient.selectOneRow("tbl_abc", "rowKey1");
    }*/

    @Test
    public void testScanTable() throws IOException {
        hBaseClient.scanTable("tbl_abc", "rowKey1");
    }
}