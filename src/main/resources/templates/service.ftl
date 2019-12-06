package ${package.parent}.${package.service};

import ${package.parent}.${package.entity}.${table.convertName};
import java.util.List;

public interface ${table.serviceName}{

    List<${table.convertName}> page(Integer pageSize,Integer pageCount);
    void update(${table.convertName} ${table.convertNameLower});
    int save(${table.convertName} ${table.convertNameLower});
    void remove(<#list table.fields as field><#if field.field == table.primaryKey>${field.transitionType} ${field.transitionFieldLower}</#if></#list>);
    int count();
    User findOne(<#list table.fields as field><#if field.field == table.primaryKey>${field.transitionType} ${field.transitionFieldLower}</#if></#list>);
    List<${table.convertName}> findAllList();


}




<#--package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    }
</#if>-->
