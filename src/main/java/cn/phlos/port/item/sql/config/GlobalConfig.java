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
    private String oupFile = "D://";

    /**
     * 切除对应的头
     */
    private String prefix[] ;

    /**
     *
     */
    private TableInfo tableInfo;

    private Connection connection;



    public List<String> getTableNameList() {
        this.tableNameList = TableUtil.getTableNames(connection);
        return this.tableNameList;
    }


    public List<TableInfo> getTableInfo(){
        List<TableInfo> list = new ArrayList<>();
        if (this.tableNameList!=null){
            for(String tableName : this.tableNameList){
                tableInfo = new TableInfo();
                tableInfo.setName(tableName);
                tableInfo.setConvertName(humpName(tableName));
                tableInfo.setFields( TableUtil.getColumnNames(tableName,connection));
                tableInfo.setPrimaryKey( TableUtil.primaryKey(tableName,connection));
                list.add(tableInfo);
            }
        }
        return list;
    }

    /**
     * 判断,如t_user_name,去掉t改为UserName
     */
    public String humpName(String tableName) {
        String letter="";
        String aa[] = tableName.split("_");

       if (prefix!=null){
           for (String name: prefix) {
               if (aa[0].equals(name)) {
                   continue;
               }
               letter += aa[0].substring(0,1).toUpperCase() + aa[0].substring(1);
           }
       }else {
           letter += aa[0].substring(0,1).toUpperCase() + aa[0].substring(1);
       }
        for(int i=1;i<aa.length;i++){

            letter+=aa[i].substring(0,1).toUpperCase()+aa[i].substring(1);
        }
        return letter;
    }


    /**
     * 对prefix判断是否存在_
     */

    public void setPrefix( String prefix) {
        this.prefix = prefix.replaceAll(",", "").split("_");
    }


    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public String getOupFile() {
        return oupFile;
    }

    public void setOupFile(String oupFile) {
        this.oupFile = oupFile;
    }
}
