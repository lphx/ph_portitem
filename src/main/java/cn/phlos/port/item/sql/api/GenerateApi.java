package cn.phlos.port.item.sql.api;

import cn.phlos.port.item.sql.builder.ConfigBuilder;
import cn.phlos.port.item.sql.config.DataSourceConfig;
import cn.phlos.port.item.sql.config.GlobalConfig;
import cn.phlos.port.item.sql.config.PackageConfig;
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

    @PostMapping("/formData")
    public List<String> formData(DataSourceConfig dataSource){
        dataSourceConfig = dataSource;
        dataSourceConfig.setDriverClass("com.mysql.cj.jdbc.Driver");
        List<String> tableNames = TableUtil.getTableNames(dataSourceConfig.getConnection());
        return tableNames;
    }

    @GetMapping("/tableFrom")
    public String tableFrom(String tablebox,String backName) throws Exception {
        System.out.println(tablebox+"    "+backName);
        GlobalConfig globalConfig = new GlobalConfig();
        //globalConfig.setOupFile(fileName);//"D://test"
        globalConfig.setTableNameList(tablebox);
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(backName);//"cn.phlos.ph_portiem_test"
        ConfigBuilder configBuilder = new ConfigBuilder(dataSourceConfig,globalConfig,packageConfig);
        configBuilder.executeApi();
        return "成功生成";
    }

}
