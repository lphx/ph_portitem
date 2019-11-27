package cn.phlos.port.item.sql.rules;

/**
 * @ClassName IColumnType
 * @Description TODO
 * @Autor lipenghong
 * @Date 22:38 2019/11/27
 **/
public interface IColumnType {
    /**
     * 获取字段类型
     *
     * @return 字段类型
     */
    String getType();

    /**
     * 获取字段类型完整名
     *
     * @return 字段类型完整名
     */
    String getPkg();
}
