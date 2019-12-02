package cn.phlos.port.item.sql.builder;

import cn.phlos.port.item.sql.config.*;
import cn.phlos.port.item.sql.engine.FreemarkerTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigBuilder {

    private PackageConfig packageConfig ;

    private TableInfo tableInfo;

    private GlobalConfig globalConfig;
    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * SQL连接
     */
    private Connection connection;

    private FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine(this);

   // private TemplateConfig templateConfig;



    public ConfigBuilder(DataSourceConfig dataSourceConfig, GlobalConfig globalConfig, PackageConfig packageConfig) {
        this.dataSourceConfig = dataSourceConfig;
        handlerDataSource(dataSourceConfig);
        this.globalConfig = globalConfig;
        this.packageConfig = packageConfig;
    }

    /**
     * 生成代码
     */
    public void execute() throws Exception {
        globalConfig.setConnection(connection);
        globalConfig.getTableNameList();
        globalConfig.setPrefix("t_");
        freemarkerTemplateEngine.create(globalConfig.getTableInfo());
        //1.生成实体类
        //createEntity();
        //2.生成mapper类

        //3.生成service类

        //4.生成service.impl

        //5.生成controller

    }
    /**
     * 测试生成实体类
     */
    public  void createEntity(List<TableField> tableInfo, String tableName) throws Exception {
        Map<String,Object> map = new HashMap<>();
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        freemarkerTemplateEngine.init();
        String oupFile = freemarkerTemplateEngine.humpName(tableName);
        String path = packageConfig.getParent()+"."+packageConfig.getEntity();
        freemarkerTemplateEngine.existsFile("D:/"+path.replaceAll("\\.","/"));

//        String entityPath = path+"/entity".replaceAll("/",".");
        map.put("entityPackage",path);
        map.put("entity",oupFile);
        map.put("table", tableInfo);//表信息
        freemarkerTemplateEngine.write(map,"entity.ftl","D:/"+path.replaceAll("\\.","/")+"/"+oupFile+".java");
    }

    /**
     * 处理数据源配置
     *
     * @param config DataSourceConfig
     */
    private void handlerDataSource(DataSourceConfig config) {
        connection = config.getConnection();

    }

    public TableInfo getTableInfo() {
        if(tableInfo==null){
            return new TableInfo();
        }
        return tableInfo;
    }

    /**
     * 所有包配置信息
     *
     * @return 包配置
     */
    public PackageConfig getPackageConfig() {
        return packageConfig;
    }

    public GlobalConfig globalConfig(){
        return globalConfig;
    }
}
