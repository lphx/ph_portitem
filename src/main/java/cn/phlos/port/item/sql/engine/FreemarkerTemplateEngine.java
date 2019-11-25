
package cn.phlos.port.item.sql.engine;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;


public class FreemarkerTemplateEngine {
    protected static final Logger logger = LoggerFactory.getLogger(FreemarkerTemplateEngine.class);

    private Configuration configuration;

    public void init(){
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class,"/templates");
    }


    public void write(Map<String,Object> map,String templatePath,String outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        template.process(map,new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8"));
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }


    /**
     * 创建文件夹
     * @param path
     */
    public void existsFile(String path) {
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        logger.debug("创建出："+path);
    }

    /**
     * 判断,如t_user_name,去掉t改为UserName
     */
    public String humpName(String tableName) {
        String letter="";
        String aa[] = tableName.split("_");
        for(int i=0;i<aa.length;i++){
            if ((aa[0].equals("T") || aa[0].equals("t")) && i==0 )
                continue;
            letter+=aa[i].substring(0,1).toUpperCase()+aa[i].substring(1);
        }
        return letter;

    }


    public static void main(String[] args) {
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        String aa = freemarkerTemplateEngine.humpName("t_t_user_name_aaa_ddd");
        System.out.println(aa);
    }


}
