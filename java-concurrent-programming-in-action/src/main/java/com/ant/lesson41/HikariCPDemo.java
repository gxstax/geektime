package com.ant.lesson41;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/4/12 9:40 上午
 */
public class HikariCPDemo {
    
    public static void main(String[] args) {
        HikariCPDemo demo= new HikariCPDemo();
        demo.execute();
    }
    
    /**
     * <p>
     * 执行
     * </p>
     * 
      * @param 
     * @return void
     */
    public void execute() {
        HikariConfig config = new HikariConfig();
        // 设置空闲数据库连接的队列 idl 最小值
        config.setMinimumIdle(1);
        // 池最大数
        config.setMaximumPoolSize(2);
        // 设置连接初始化语句
        config.setConnectionInitSql("SELECT 1");
        // 设置数据源名称
        config.setDataSourceClassName("com.mysql.jdbc.Driver");
        // 设置连接属性
        config.addDataSourceProperty("url", "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false&allowMultiQueries=true");

        // 创建数据源
        DataSource ds = new HikariDataSource(config);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = ds.getConnection();
            // 创建 Statement
            stmt = conn.createStatement();
            // 获取结果
            rs = stmt.executeQuery("select * from user");

            while (rs.next()) {
                // 输出 ID
                System.out.println(rs.getInt(1));
                // 输出姓名
                System.out.println(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭 ResultSet
            close(rs);
            // 关闭 Statement
            close(stmt);
            // 关闭 Connection
            close(conn);
        }
    }
    
    /**
     * <p>
     * 关闭资源
     * </p>
     * 
      * @param source
     * @return void
     */
    public void close(AutoCloseable source) {
        if (null != source) {
            try {
                source.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
