package cn.phlos.port.item.sql.builder;

import cn.phlos.port.item.sql.config.PackageConfig;
import cn.phlos.port.item.sql.config.TableField;
import cn.phlos.port.item.sql.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigBuilder {

    private PackageConfig packageConfig= new PackageConfig();


    /**
     * 测试生成实体类
     */
    public  void shiti(List<TableField> tableInfo, String tableName) throws Exception {
        Map<String,Object> map = new HashMap<>();
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        freemarkerTemplateEngine.init();
        String oupFile = freemarkerTemplateEngine.humpName(tableName);
        String path = packageConfig.getParent()+"."+packageConfig.getEntity();
        freemarkerTemplateEngine.existsFile("D:/"+path.replaceAll("\\.","/"));

//        String entityPath = path+"/entity".replaceAll("/",".");
        map.put("entityPackage",path);
        map.put("entity",oupFile);
        map.put("table", tableInfo);//表信息
        freemarkerTemplateEngine.write(map,"entity.ftl","D:/"+path.replaceAll("\\.","/")+"/"+oupFile+".java");
    }

}
