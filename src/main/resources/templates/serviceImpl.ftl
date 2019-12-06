package ${package.parent}.${package.serviceImpl};

import ${package.parent}.${package.entity}.${table.convertName};
import ${package.parent}.${package.mapper}.${table.mapperName};
import ${package.parent}.${package.service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.mapperNameLower};

    public  List<${table.convertName}> page(Integer pageSize,Integer pageCount) {
        return ${table.mapperNameLower}.page(pageSize,pageCount);
    }

    public void update(${table.convertName} ${table.convertNameLower}) {
        ${table.mapperNameLower}.update(${table.convertNameLower});
    }

    public int save(${table.convertName} ${table.convertNameLower}) {
        return ${table.mapperNameLower}.save(${table.convertNameLower});
    }

    <#list table.fields as field><#if field.field == table.primaryKey>
    public void remove(${field.transitionType} ${field.transitionFieldLower}) {
        ${table.mapperNameLower}.remove(id);
    }
    </#if></#list>

    public int count() {
        return ${table.mapperNameLower}.count();
    }

    <#list table.fields as field><#if field.field == table.primaryKey>
    public User findOne(${field.transitionType} ${field.transitionFieldLower}) {
        return ${table.mapperNameLower}.findOne(${field.transitionFieldLower});
    }
    </#if></#list>

    public List<${table.convertName}> findAllList() {
        return ${table.mapperNameLower}.findAllList();
    }

}





<#--package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
* <p>
    * ${table.comment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    }
</#if>-->
