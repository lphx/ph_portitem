package ${package.parent}.${package.entity};

import lombok.Data;


@Data
public class ${table.entityName} {




<#-- 循环属性名称 -->
<#list table.fields as field>
    <#--<#if field.comment??>
        /**
        * ${field.comment}
        */
    </#if>-->
    <#--<#if field.keyIdentityFlag>
        @TableId(value="${field.name}", type= IdType.AUTO)
    </#if>-->
    private ${field.transitionType} ${field.transitionField};

</#list>
}

<#--package ${entityPackage};-->
<#--import ${entityPackage}.${entity};-->
<#--import java.io.Serializable;-->

<#--/**-->
<#--* 描述: ${table.comment}-->
<#--* author: ${author}-->
<#--* date: ${date}-->
<#--*/-->
<#--@TableName("${table.name}")-->
<#--public class ${entity} implements Serializable {-->


<#--private static final long serialVersionUID = 1L;-->
<#--&lt;#&ndash; 循环属性名称 &ndash;&gt;-->
<#--<#list table.fields as field>-->
<#--    <#if field.comment??>-->
<#--        /**-->
<#--        * ${field.comment}-->
<#--        */-->
<#--    </#if>-->
<#--    <#if field.keyIdentityFlag>-->
<#--        @TableId(value="${field.name}", type= IdType.AUTO)-->
<#--    </#if>-->
<#--    private ${field.propertyType} ${field.propertyName};-->

<#--</#list>-->
<#--&lt;#&ndash; 循环set/get方法 &ndash;&gt;-->
<#--<#list table.fields as field>-->
<#--    <#if field.propertyType == "Boolean">-->
<#--        <#assign getprefix="is"/>-->
<#--    <#else>-->
<#--        <#assign getprefix="get"/>-->
<#--    </#if>-->
<#--    public ${field.propertyType} ${getprefix}${field.capitalName}() {-->
<#--    return ${field.propertyName};-->
<#--    }-->

<#--    public void set${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName}) {-->
<#--    this.${field.propertyName} = ${field.propertyName};-->
<#--    }-->
<#--</#list>-->
<#--}-->
