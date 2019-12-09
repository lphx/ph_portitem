package cn.phlos.port.item.sql.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Component
public class DataSourceConfig {


    private String ipAddr;

    private String port;

    private String dataSource;

    private String driverClass;

    private String url;

    private String username;

    private String password;




    public  Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(this.driverClass);
           // if (this.ipAddr.equals("")&&this.port.equals("")&&this.dataSource.equals("")){
            this.url = "jdbc:mysql://"+this.url+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                conn = DriverManager.getConnection(this.url, this.username, this.password);
           /* }else {
                //"jdbc:mysql://120.78.151.208:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"
                String pageUrl = "jdbc:mysql://"+ipAddr+":"+port+"/"+dataSource+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                conn =  DriverManager.getConnection(pageUrl, this.username, this.password);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



}
