
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

    //判断,如t_user_name,去掉t改为UserName
    public void humpName(String oupFile) {
        int index = 0,lastIndex = 0;
        int fileLength = oupFile.length();
        index=oupFile.indexOf("_");
            String  letter = oupFile.substring(0,index);
            System.out.println("str = " + letter);
            //如果_为t就去除
            if (letter.equals("t")||letter.equals("T")){
               // letter = oupFile.substring(index+1,1);
               
            }
            while ((index = oupFile.indexOf("_"))>0){
                lastIndex= oupFile.indexOf("_",index+1);
                oupFile = oupFile.substring(index+1,lastIndex);
                System.out.println("letter = " + oupFile);
            }


    }

    public static void main(String[] args) {
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        freemarkerTemplateEngine.humpName("t_user_name");
    }
}
