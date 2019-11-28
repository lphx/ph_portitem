package cn.phlos.port.item.sql;

import cn.phlos.port.item.sql.builder.ConfigBuilder;
import cn.phlos.port.item.sql.config.DataSourceConfig;
import cn.phlos.port.item.sql.config.TableField;
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
    protected static final Logger LOGGER = LoggerFactory.getLogger(Generate.class);

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

    public static void main(String[] args) throws Exception {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://120.78.151.208:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("li15775063262");
        dataSourceConfig.init();
        Connection conn = dataSourceConfig.getConnection();
        List<String> tableList = getTableNames(conn);
        for (String tableName : tableList){
            List<TableField> table1 = getColumnNames(tableName,conn);
            new ConfigBuilder().shiti(table1,tableName);
        }




    }



    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames(Connection conn) {
        List<String> tableNames = new ArrayList<>();

        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables("test", null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
//                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     */
    public static  List<TableField>  getColumnNames( String tableName, Connection conn) {
        List<TableField> table = new ArrayList<>();
        MySqlTypeConvert mySqlTypeConvert = new MySqlTypeConvert();
        //与数据库的连接
        PreparedStatement pStemt = null;
        ResultSet rs = null;
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            //获取单个表下的内容
            rs = dbMetaData.getColumns(null, "%", tableName, "%");
            while (rs.next()) {
                TableField tableField = new TableField();
                tableField.setTableName(tableName);
                tableField.setField(rs.getString("COLUMN_NAME"));
                tableField.setType(rs.getString("TYPE_NAME"));
                tableField.setTransitionType(mySqlTypeConvert.processTypeConvert(tableField.getType()).getType());
                tableField.setTypeSize(rs.getString("COLUMN_SIZE"));
                tableField.setComment(rs.getString("REMARKS"));
                table.add(tableField);


            }


        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    rs.close();
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return table;
    }




}
