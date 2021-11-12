package com.lai.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Description: MyBatisPlusGenerator-自动生成实体 (与mybatis generator两选一)
 * @Author: laixues
 * @Date: 2021/1/28
 */
@MapperScan("com.example.demo.mapper")
public class MyBatisPlusGenerator {

    static String packageName = "com.lai"; // 当前包名
    static String author = "MyBatisPlusGenerator"; // 作者
    static String sqlUrl = "mysql://localhost:3306/"; // 数据库类型及地址
    static String sqlDb = "51-music"; // 数据库名
    static String sqlUser = "root";
    static String sqlPassword = "123456";
    static String table = "user,sys_role,sys_menu,sys_user_role,sys_role_menu"; // 表名，多个用逗号隔开


    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java"); // 当前项目所在的系统目录路径 /Users/laixueshi/applicationApp/idea/test/springboot-test01
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:" + sqlUrl + sqlDb + "?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(sqlUser);
        dsc.setPassword(sqlPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName); // 父级包名
        pc.setController("controller.generator");
        pc.setEntity("entity.generator"); // 设置自定义实体类包名 下同
        pc.setMapper("mapper.generator");
        pc.setService("service.generator");
        pc.setServiceImpl("service.impl.generator");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null); // 不生成MapperXML 下同
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true); // 是否使用lombok注解
        strategy.setEntityTableFieldAnnotationEnable(true); // 是否生成字段注解:读取表中的注释
        strategy.setRestControllerStyle(true); // 是否生成@RestController注解的controller
        // strategy.setSuperControllerClass("com.example.open.web.controller.BaseController"); // controller共同继承的公共父类
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        strategy.setInclude(table.split(",")); // 设置生成的表名
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix("t_"); // 表前缀，如t_user，没有就注释掉此行
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute(); // 执行
    }



}
