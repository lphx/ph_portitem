package cn.phlos.port.item.sql.util;

import cn.phlos.port.item.sql.Generate;
import cn.phlos.port.item.sql.config.TableField;
import cn.phlos.port.item.sql.rules.MySqlTypeConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Autor lipenghong
 * @Date 22:42 2019/11/28
 **/
public class TableUtil {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TableUtil.class);

    //private Connection conn;

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
            rs = db.getTables(conn.getCatalog(), null, null, new String[] { "TABLE" });
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
    public static List<TableField> getColumnNames(String tableName, Connection conn) {
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
                tableField.setTransitionField(humpName(tableField.getField()));
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
                    //conn.close();
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return table;
    }

    /**
     * 获取主键
     */
    public static String primaryKey(String tableName,Connection conn){
        String primaryKey=null;
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = null;
            rs = dbMetaData.getPrimaryKeys(null, null, tableName);

            while (rs.next()) {
                primaryKey = rs.getString("COLUMN_NAME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return primaryKey;
    }

    /**
     * 判断,如t_user_name,去掉t改为UserName
     */
    private static String humpName(String tableName) {

        String aa[] = tableName.split("_");
        String letter=aa[0];
        for(int i=1;i<aa.length;i++){

            letter+=aa[i].substring(0,1).toUpperCase()+aa[i].substring(1);
        }
        return letter;
    }

}
