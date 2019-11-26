package cn.phlos.port.item.sql;

import cn.phlos.port.item.sql.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Generate 测试生成代码类
 * @Description TODO
 * @Autor lipenghong
 * @Date 22:22 2019/11/24
 **/
public class Generate {

    //测试路径
    private static String path = "D:/test/aaaa";
    private static String tableName = "t_user_name";


    public static void main(String[] args) throws Exception {
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        freemarkerTemplateEngine.init();

        String oupFile = freemarkerTemplateEngine.humpName(tableName);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("classPath", "com.freemark.hello");
        dataMap.put("className", "AutoCodeDemo");
        dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
        freemarkerTemplateEngine.existsFile(path+"/controller");
        freemarkerTemplateEngine.write(dataMap,"controller.ftl",path+"/controller/"+oupFile+"Controller.java");
        freemarkerTemplateEngine.existsFile(path+"/service");
        freemarkerTemplateEngine.write(dataMap,"service.ftl",path+"/service/"+oupFile+"Service.java");
        freemarkerTemplateEngine.existsFile(path+"/service/impl");
        freemarkerTemplateEngine.write(dataMap,"serviceImpl.ftl",path+"/service/impl/"+oupFile+"ServiceImpl.java");
        freemarkerTemplateEngine.existsFile(path+"/mapper");
        freemarkerTemplateEngine.write(dataMap,"mapper.ftl",path+"/mapper/"+oupFile+"Mapper.java");
        freemarkerTemplateEngine.existsFile(path+"/entity");
        freemarkerTemplateEngine.write(dataMap,"entity.ftl",path+"/entity/"+oupFile+".java");
    }

}
