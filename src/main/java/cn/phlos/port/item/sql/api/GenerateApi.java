package cn.phlos.port.item.sql.api;

import cn.phlos.port.item.sql.config.DataSourceConfig;
import cn.phlos.port.item.sql.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenerateApi {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @GetMapping("/formData")
    public List<String> formData(String url,String username,String password){
        dataSourceConfig.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setPassword(password.replace("111,",""));
        dataSourceConfig.setUsername(username);
        List<String> tableNames = TableUtil.getTableNames(dataSourceConfig.getConnection());
        return tableNames;
    }

}
