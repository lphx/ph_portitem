package ${package.parent}.${package.controller}

import ${package.parent}.${package.entity}.${table.convertName};
import ${package.parent}.${package.service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/${table.convertNameLower}")
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${table.serviceNameLower};

    @PostMapping("/page")
    public  List<${table.entityName}> page(Integer pageSize,Integer pageCount) {
        return ${table.serviceNameLower}.page(pageSize,pageCount);
    }

    @PutMapping("/update")
    public String update(${table.entityName} ${table.convertNameLower}) {
        ${table.serviceNameLower}.update(${table.convertNameLower});
        return "success";
    }

    @PostMapping("save")
    public int save(${table.entityName} ${table.convertNameLower}) {
        int count = ${table.serviceNameLower}.save(${table.convertNameLower});
        return count;
    }

    @DeleteMapping("/remove")
    <#list table.fields as field><#if field.field == table.primaryKey>
    public String remove(${field.transitionType} ${field.transitionFieldLower}) {
        ${table.serviceNameLower}.remove(${field.transitionFieldLower});
        return "success";
    }
    </#if></#list>

    @GetMapping("/count")
    public int count() {
        return ${table.serviceNameLower}.count();
    }

    @GetMapping("findById")
    <#list table.fields as field><#if field.field == table.primaryKey>
    public ${table.entityName} findById(${field.transitionType} ${field.transitionFieldLower}) {
        return ${table.serviceNameLower}.findOne(${field.transitionFieldLower});
    }
    </#if></#list>

    @GetMapping("/findAll")
    public List<${table.entityName}> findAll() {
        return ${table.serviceNameLower}.findAllList();
    }

}



<#--package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>

    }
</#if>-->
