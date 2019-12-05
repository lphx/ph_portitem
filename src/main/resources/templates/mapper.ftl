package ${package.parent}.${package.mapper};

import ${package.parent}.${package.entity}.${table.convertName};
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * ${table.convertName} mapper接口
 */
@Mapper
public interface ${table.mapperName}{

    /**
      *获取所有数据
      */
    @Select({"SELECT id, name,password,phone FROM ${table.name}"})
    @Results(id = "select${table.convertName}",value={
    <#list table.fields as field>
    @Result(id=true,column="${field.field}",property="${field.transitionField}",javaType=${field.transitionType}.class),
    </#list>
    })
    List<${table.convertName}> findAllList();

    @Select("SELECT <#list table.fields as field>${field.field}<#if field_index != table.fields?size-1>,</#if></#list> FROM ${table.name} WHERE ${table.primaryKey} = <#list table.fields as field><#if field.field == table.primaryKey>${r"#{"}${field.transitionField}${r"}"}</#if></#list>")
    @ResultMap(value = "select${table.convertName}")
    User findOne(@Param("<#list table.fields as field><#if field.field == table.primaryKey>${field.transitionField}</#if></#list>") Integer id);



    @Insert("INSERT INTO  ${table.name}( <#list table.fields as field>${field.field}<#if field_index != table.fields?size-1>,</#if></#list>) VALUES(<#list table.fields as field>${r"#{"}${field.transitionField}${r"}"}<#if field_index != table.fields?size-1>,</#if></#list>)")
    int save(${table.convertName} ${table.convertNameLower});

    @Select("SELECT * FROM  ${table.name} limit ${r"#{pageSize}"},${r"#{pageCount}"})")
    List<User> page(Param("pageSize")Integer pageSize,Param("pageCount")Integer pageCount);

    @Update("UPDATE  <#list table.fields as field>${field.field}=${r"#{"}${field.transitionField}${r"}"}<#if field_index != table.fields?size-1>,</#if></#list> where ${table.primaryKey}= <#list table.fields as field><#if field.field == table.primaryKey>${r"#{"}${field.transitionField}${r"}"}</#if></#list>")
    void update(${table.convertName} ${table.convertNameLower});



    @Delete("DELETE FROM  ${table.name} where ${table.primaryKey} = <#list table.fields as field><#if field.field == table.primaryKey>${r"#{"}${field.transitionField}${r"}"}</#if></#list>")
    void remove(@Param("<#list table.fields as field><#if field.field == table.primaryKey>${field.transitionField}</#if></#list>") Integer id);

    @Select("SELECT COUNT(*) FROM  ${table.name}")
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
