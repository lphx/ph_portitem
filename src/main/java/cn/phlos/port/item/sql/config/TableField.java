package cn.phlos.port.item.sql.config;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class TableField {

    private String tableName;
    private String tableType;
    private String comment;

   /* private List<TableField> tableList;

    private Map<String,List<TableField>> tableMap;*/


    public TableField(){}

    public TableField(String tableName, String tableType, String comment) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.comment = comment;
    }
}
