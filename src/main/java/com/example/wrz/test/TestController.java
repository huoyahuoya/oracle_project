package com.example.wrz.test;

import com.example.wrz.util.JDBCUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        System.out.println("测试开始");
        test_connect();
        return "测试开始???";
    }

    private void test_connect() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("test_connect");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from wrz_test";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("NAME");
                String password = rs.getString("PASSWORD");
                System.out.println("springboot_00 log: name = " + name + ", password = " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
    }
}
//http://localhost:8080/test