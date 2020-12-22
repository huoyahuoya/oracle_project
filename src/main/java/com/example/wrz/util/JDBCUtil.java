package com.example.wrz.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {

    private static String url = "";
    private static String user = "";
    private static String password = "";

    static {
        Properties prop = new Properties();
        try {
            InputStream is = JDBCUtil
                    .class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");
            System.out.println("wrz_log is = " + is + ",prop = " + prop);
            prop.load(is);
            String driverClassName = prop.getProperty("driver");
            url = prop.getProperty("spring.datasource.url");
            user = prop.getProperty("spring.datasource.username");
            password = prop.getProperty("spring.datasource.password");

            System.out.println("wrz_log url = " + url);
            System.out.println("wrz_log user = " + user);
            System.out.println("wrz_log password = " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, Statement stat, ResultSet rs) {
        close(conn, stat);
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stat) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
