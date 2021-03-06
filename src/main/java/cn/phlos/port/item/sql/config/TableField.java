package cn.phlos.port.item.sql.config;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class TableField {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 表字段
     */
    private String field;
    /**
     * 转换表字段名
     */
    private String transitionField;
    /**
     * 转换表字段名
     */
    private String transitionFieldLower;
    /**
     * 字段类型
     */
    private String type;

    /**
     * 转化类型
     */
    private String transitionType;
    /**
     * 类型长度
     */
    private String typeSize;
    /**
     * 字段备注
     */
    private String comment;



   /* private List<TableField> tableList;

    private Map<String,List<TableField>> tableMap;*/


    public TableField(){}

}
