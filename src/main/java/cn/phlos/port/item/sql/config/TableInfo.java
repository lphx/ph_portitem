package cn.phlos.port.item.sql.config;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Autor lipenghong
 * @Date 22:32 2019/11/28
 **/
@Data
public class TableInfo {

    private final Set<String> importPackages = new HashSet<>();
    private boolean convert;
    private String name;
    private String comment;
    private String entityName;
    private String mapperName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private List<TableField> fields;

}
