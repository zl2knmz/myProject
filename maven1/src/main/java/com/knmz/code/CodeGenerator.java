package com.knmz.code;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {

        String url = "192.168.16.231";                       //数据库连接名
        String port = "3306";                           //端口号
        String dataBase = "accupasscn";                        //数据库名
        String userName = "root";                       //用户名
        String password = "root";                     //密码
        String baseProjectPackage = "com";     //包路径
        String model = "";                          //模块名称
        String author = "";
        String[] tableList = {"live_room_online"};
        String baseController = "com.xw.core.base.BaseController";  //继承的baseController
        String projectPath = "D:/develop/GithubProjects/myProject/maven1";               //项目路径


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setActiveRecord(true); //继承和实现
        gc.setSwagger2(true); //实体属性 开启Swagger2 注解
        gc.setDateType(DateType.TIME_PACK);
        gc.setIdType(IdType.INPUT);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + url + ":" + port + "/" + dataBase + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowMultiQueries=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

//        String modelName = "";
//        if (!StringUtils.isBlank(model)){
//            modelName = "." + model;
//        }
        // 包配置
        mpg.setPackageInfo(new PackageConfig()
                        .setModuleName(model)
                        .setParent(baseProjectPackage)// 自定义包路径
//                .setController(projectName + ".controller" + modelName)// 这里是控制器包名，默认 web
//                .setEntity(projectName + ".entity" + modelName)
//                .setMapper(projectName + ".mapper" + modelName)
//                .setService(projectName + ".service" + modelName)
//                .setServiceImpl(projectName + ".service.impl" + modelName)
//                .setXml("mapper")
        );

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置 xml文件

        /*List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + model
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);*/

        //自定义覆盖文件
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            //如果是Entity则直接返回true表示写文件
            if (fileType == FileType.ENTITY) {
                return true;
            }
            //否则先判断文件是否存在
            File file = new File(filePath);
            boolean exist = file.exists();
            if (!exist) {
                file.getParentFile().mkdirs();
            }
            //文件不存在或者全局配置的fileOverride为true才写文件
            return !exist || configBuilder.getGlobalConfig().isFileOverride();
        });
        mpg.setCfg(cfg);

        // 配置模板

        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);

        templateConfig.setController(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);

        mpg.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass(Model.class);
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass(baseController);
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableList);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(model + "_");
        strategy.setEntityColumnConstant(true);
        strategy.setEntityTableFieldAnnotationEnable(true);

        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
