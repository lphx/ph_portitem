package ${package.parent}.${package.mapper};

import ${package.parent}.${package.entity}.${table.name};

@Mapper
public interface ${table.mapperName}{

    @Select({"SELECT id, name,password,phone FROM ${table.name}"})
    @Results(id = "select${table.convertName}",value={
    <#list table.fields as field>
    @Result(id=true,column="${field.field}",property="${field.transitionField}",javaType=${field.transitionType}.class),
    </#list>
    })
    List<${table.convertName}> findAllList();

    @Select("SELECT id, name,password,phone FROM t_user WHERE id = ${r"#{"}${table.convertName}${r"}"}")
    @ResultMap(value = "select${table.convertName}")
    User findOne(@Param("id") Integer id);

    <#--@Insert("INSERT INTO  ${table.name}(name, password, phone) VALUES(#{name}, #{password}, #{phone})")
    int save(User user);

    @Select("SELECT * FROM  ${table.name} limit #{page.pageSize},#{page.pageCount})")
    List<User> page(Page page);

    @Update("UPDATE  ${table.name} SET name=#{name},password=#{password},phone=#{phone} where id=#{id} ")
    void update(User user);



    @Delete("DELETE FROM  ${table.name} where id = #{id}")
    void remove(Integer id);

    @Select("SELECT COUNT(*) FROM  ${table.name}")
    int count();-->



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
