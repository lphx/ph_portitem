
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
        int position=0,secondPosition=0;
        String letter="";
        int tableNameLength = tableName.replaceAll("_","").length();
        while((position=tableName.indexOf("_",position))>0){
            letter+=subtableName(tableName,secondPosition,position);
            //如果_为t就去除
            if (letter.equals("T")){
                letter="";
            }
            position+=1;
            secondPosition=position;
        }
        if (tableNameLength>letter.length()){
            letter+=subtableName(tableName,secondPosition,tableName.length());
        }
        return letter;

    }

    /**
     *对字符串首字母进行变换大写
     */
    private String subtableName(String tableName,int secondPosition,int position ){
        tableName =tableName.substring(secondPosition,position);
        return tableName.substring(0,1).toUpperCase()+tableName.substring(1);
    }


    public static void main(String[] args) {
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        freemarkerTemplateEngine.humpName("t_user_name");
    }


}
