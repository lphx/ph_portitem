package cn.phlos.port.item.sql.config;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class DataSourceConfig {


    private String driverClass;

    private String url;

    private String username;

    private String password;


    public void init(){
        try {
            Class.forName(this.driverClass);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("数据库连接失败");
        }
    }

    public  Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



}
