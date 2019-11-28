package cn.phlos.port.item.sql;

import cn.phlos.port.item.sql.builder.ConfigBuilder;
import cn.phlos.port.item.sql.config.DataSourceConfig;
import cn.phlos.port.item.sql.config.TableField;
import cn.phlos.port.item.sql.config.TableInfo;
import cn.phlos.port.item.sql.engine.FreemarkerTemplateEngine;
import cn.phlos.port.item.sql.rules.DbColumnType;
import cn.phlos.port.item.sql.rules.IColumnType;
import cn.phlos.port.item.sql.rules.MySqlTypeConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * @ClassName Generate 测试生成代码类
 * @Description TODO
 * @Autor lipenghong
 * @Date 22:22 2019/11/24
 **/
public class Generate {


    //测试路径
    private static String path = "D:/test/aaaa";


    public void test1() throws Exception {
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        freemarkerTemplateEngine.init();

        String oupFile = freemarkerTemplateEngine.humpName("tableName");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("classPath", "com.freemark.hello");
        dataMap.put("className", "AutoCodeDemo");
        dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
        freemarkerTemplateEngine.existsFile(path+"/controller");
        freemarkerTemplateEngine.write(dataMap,"controller.ftl",path+"/controller/"+oupFile+"Controller.java");
        freemarkerTemplateEngine.existsFile(path+"/service");
        freemarkerTemplateEngine.write(dataMap,"service.ftl",path+"/service/"+oupFile+"Service.java");
        freemarkerTemplateEngine.existsFile(path+"/service/impl");
        freemarkerTemplateEngine.write(dataMap,"serviceImpl.ftl",path+"/service/impl/"+oupFile+"ServiceImpl.java");
        freemarkerTemplateEngine.existsFile(path+"/mapper");
        freemarkerTemplateEngine.write(dataMap,"mapper.ftl",path+"/mapper/"+oupFile+"Mapper.java");
        freemarkerTemplateEngine.existsFile(path+"/entity");
        freemarkerTemplateEngine.write(dataMap,"entity.ftl",path+"/entity/"+oupFile+".java");
    }

    public static void main(String[] args){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://120.78.151.208:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("li15775063262");
        ConfigBuilder configBuilder = new ConfigBuilder(dataSourceConfig);
        configBuilder.execute();


        /*List<String> tableList = getTableNames(conn);
        tableInfo.getFields();
        for (String tableName : tableList){
            List<TableField> table1 = getColumnNames(tableName,conn);
            new ConfigBuilder().createEntity(table1,tableName);
        }*/




    }









}
