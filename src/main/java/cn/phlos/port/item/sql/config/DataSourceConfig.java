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




    public  Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(this.driverClass);
            conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



}
