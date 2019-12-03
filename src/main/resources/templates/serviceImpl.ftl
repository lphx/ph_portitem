package ${package.parent}.${package.serviceImpl};

import ${package.parent}.${package.entity}.${table.name};
import ${package.parent}.${package.mapper}.${table.mapperName};
import ${package.parent}.${package.service}.${table.serviceName};
import org.springframework.stereotype.Service;


@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

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
