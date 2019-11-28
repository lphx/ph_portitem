package cn.phlos.port.item.sql.config;

import cn.phlos.port.item.sql.util.TableUtil;
import lombok.Data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Autor lipenghong
 * @Date 23:04 2019/11/28
 **/

public class GlobalConfig {

    /**
     * 所有表名
     */
    private List<String> tableNameList;
    /**
     * 输出路径
     */
    private String oupFile = "D:/";
    /**
     * 切除对应的头
     */
    private List<String> prefix;

    /**
     *
     */
    private TableInfo tableInfo;

    /*public List<String> getTableNameList(Connection connection) {
        this.tableNameList = TableUtil.getTableNames(connection);
        return this.tableNameList;
    }


    public List<TableInfo> getTableInfo(){
        List<TableInfo> list = new ArrayList<>();
        if (this.tableNameList!=null){
            for(String tableName : this.tableNameList){
                TableUtil.getColumnNames(tableName,)
            }
        }
        return list;
    }*/

}
