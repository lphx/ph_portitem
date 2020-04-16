package ${package.parent}.${package.mapper};

import ${package.parent}.${package.entity}.${table.entityName};
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * ${table.convertName}Mapper接口
 */
@Mapper
public interface ${table.mapperName}{

    /**
      *获取所有数据
      */
    @Select("SELECT <#list table.fields as field>${field.field} as ${field.transitionField}<#if field_index != table.fields?size-1>,</#if></#list> FROM `${table.name}`")
    <#--@Results(id = "select${table.convertName}",value={
    <#list table.fields as field>
    @Result(id=true,column="${field.field}",property="${field.transitionField}",javaType=${field.transitionType}.class),
    </#list>
    })-->
    List<${table.entityName}> findAllList();

    /**
     * 查询一条数据
     */
    @Select("SELECT <#list table.fields as field>${field.field} as ${field.transitionField}<#if field_index != table.fields?size-1>,</#if></#list> FROM `${table.name}` WHERE ${table.primaryKey} = <#list table.fields as field><#if field.field == table.primaryKey>${r"#{"}${field.transitionField}${r"}"}</#if></#list>")
    <#--@Results(id = "select${table.convertName}")-->
    ${table.entityName} findOne(@Param("<#list table.fields as field><#if field.field == table.primaryKey>${field.transitionField}") ${field.transitionType} ${field.transitionFieldLower}</#if></#list>);


    /**
     * 添加数据
     */
    @Insert("INSERT INTO  `${table.name}` ( <#list table.fields as field>${field.field}<#if field_index != table.fields?size-1>,</#if></#list>) VALUES(<#list table.fields as field>${r"#{"}${field.transitionField}${r"}"}<#if field_index != table.fields?size-1>,</#if></#list>)")
    int save(${table.entityName} ${table.convertNameLower});

    /**
     * 分页查询数据
     */
    @Select("SELECT <#list table.fields as field>${field.field} as ${field.transitionField}<#if field_index != table.fields?size-1>,</#if></#list> FROM  `${table.name}` limit ${r"#{pageSize}"},${r"#{pageCount}"})")
  <#--  @Results(id = "select${table.convertName}")-->
    List<${table.entityName}> page(@Param("pageSize")Integer pageSize,@Param("pageCount")Integer pageCount);

    /**
     * 更新数据
     */
    @Update("UPDATE `${table.name}` set <#list table.fields as field>${field.field}=${r"#{"}${field.transitionField}${r"}"}<#if field_index != table.fields?size-1>,</#if></#list> where ${table.primaryKey} = <#list table.fields as field><#if field.field == table.primaryKey>${r"#{"}${field.transitionField}${r"}"}</#if></#list>")
    void update(${table.entityName} ${table.convertNameLower});


    /**
     * 删除数据
     */
    @Delete("DELETE FROM  `${table.name}` where ${table.primaryKey} = <#list table.fields as field><#if field.field == table.primaryKey>${r"#{"}${field.transitionField}${r"}"}</#if></#list>")
    void remove(@Param("<#list table.fields as field><#if field.field == table.primaryKey>${field.transitionField}") ${field.transitionType} ${field.transitionFieldLower}</#if></#list>);

    /**
     * 表的数据条数
     */
    @Select("SELECT COUNT(*) FROM  `${table.name}`")
    int count();


}

<#--/**
* <p>
    * ${table.comment!} Mapper 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
    public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    }
</#if>-->
