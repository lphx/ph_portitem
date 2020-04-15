
package cn.phlos.port.item.sql.engine;


import cn.phlos.port.item.sql.builder.ConfigBuilder;
import cn.phlos.port.item.sql.config.PackageConfig;
import cn.phlos.port.item.sql.config.TableInfo;
import cn.phlos.port.item.sql.util.CompressUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class FreemarkerTemplateEngine {
    protected static final Logger logger = LoggerFactory.getLogger(FreemarkerTemplateEngine.class);

    /**
     * 配置信息
     */
    private ConfigBuilder configBuilder;


    private Configuration configuration;

    public void init(){

    }


    public void write(Map<String,Object> map,String templatePath,String outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
//        File file = new File(outputFile);
        existsNewFile(outputFile);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");
        template.process(map,osw);
        osw.close();
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }


    public void create(List<TableInfo> tableInfo) throws Exception {
        //创建实体
        String oupFile = configBuilder.globalConfig().getOupFile();
        String ospath = osLinuxOrWindows(oupFile);
        String path =ospath+"/"+configBuilder.getPackageConfig().getParent().replaceAll("\\.","/")+"/";
        System.out.println("============ = " + path);
        for (TableInfo table :tableInfo){
            Map<String,Object> objectMap=getObjectMap(table);
            PackageConfig packageConfig = new PackageConfig();

            //entity
            String entityFile =path+packageConfig.getEntity()+"/";
            existsFile(entityFile);
            write(objectMap,"entity.ftl",entityFile+table.getEntityName()+".java");
            //mapper
            String mapperFile = path+packageConfig.getMapper()+"/";
            existsFile(mapperFile);
            write(objectMap,"mapper.ftl",mapperFile+table.getMapperName()+".java");
            //service
            String serviceFile = path+packageConfig.getService()+"/";
            existsFile(serviceFile);
            write(objectMap,"service.ftl",serviceFile+table.getServiceName()+".java");
            //serviceImpl
            String serviceImplFile = path+packageConfig.getServiceImpl().replaceAll("\\.","/")+"/";
            existsFile(serviceImplFile);
            write(objectMap,"serviceImpl.ftl",serviceImplFile+table.getServiceImplName()+".java");
            //controller
            String controllerFile = path+packageConfig.getController()+"/";
            existsFile(controllerFile);
            write(objectMap,"controller.ftl",controllerFile+table.getControllerName()+".java");
            logger.debug("---------------------------------1--成功生成表："+table.getName()+"的文件-----------------------------------------------");
        }
        String CompressPath = "/CompressFile";
        CompressUtil.generateFile(ospath,"zip",CompressPath);
        //CompressUtil.delAllFile(new File(ospath));
        open(ospath);
    }


    public Map<String,Object> getObjectMap(TableInfo tableInfo){
        Map<String,Object> objectMap = new TreeMap<>();
        ConfigBuilder configBuilder = this.configBuilder;
        objectMap.put("package",configBuilder.getPackageConfig());
        objectMap.put("table",tableInfo);
        return objectMap;
    }

    //获取系统类型
    private static final String os = System.getProperty("os.name");
    //linux文件目录
    private static final String filePathlINUX = "/home/aa/bb/cc";
    private static boolean flagWind = false;
    /**
     * 创建文件夹
     * @param path
     */
    public void existsFile(String path) {
        //获取系统类型

        File file = new File(path);

        //linux平台，写入权限
        if(flagWind){
            file.setWritable(true, false);
        }
        if (!file.exists()){

            file.mkdirs();

        }
        System.out.println("path = " + path);
    }

    public void existsNewFile(String path) {
        File file = new File(path);


        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("创建出："+path);
    }


    public String osLinuxOrWindows(String path){
        if(!os.contains("Windows")){
            flagWind=true;
           return filePathlINUX;
        }
        flagWind=false;
        return path;
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


    public FreemarkerTemplateEngine(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class,"/templates");
    }
    public FreemarkerTemplateEngine() {
    }

    /**
     * 打开输出目录
     */
    public void open(String path) {
        String outDir =path;
        if (!path.equals("")) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    } else {
                        logger.debug("文件输出目录:" + outDir);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
