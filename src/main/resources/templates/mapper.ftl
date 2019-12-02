package ${package.parent}.${package.mapper};

import ${package.parent}.${package.entity}.${table.name};

@Mapper
public interface ${table.mapperName}{


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
