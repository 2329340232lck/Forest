//package com.demo.forest.util;
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
//import java.util.Scanner;
//
//
///**
// * 代码生成器，快速生成entity,controller,service等代码
// *
// * @author lck
// */
//public class MySQLGenerator {
//    //启动生成器
//    public static void main(String[] args) {
//        AutoGenerator generator = new AutoGenerator();
//        generator.setDataSource(dataSourceConfig());
//        generator.setGlobalConfig(globalConfig());
//        generator.setPackageInfo(packageConfig());
//        generator.setStrategy(strategyConfig());
//        generator.execute();
//    }
//    //扫描器
//    private static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入" + tip + ":");
//        if (scanner.hasNext()) {
//            String next = scanner.next();
//            if (StringUtils.isNotEmpty(next)) {
//                return next;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//    //全局配置
//    private static GlobalConfig globalConfig() {
//        GlobalConfig globalConfig = new GlobalConfig();
//        //设置输出目录
//        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
//        //设置开发人员名字
//        globalConfig.setAuthor("lck");
//        //是否打开输出目录(默认true)
//        globalConfig.setOpen(false);
//        //设置实体类命名方法(%s为占位符)
//        globalConfig.setEntityName("%sInfo");
//        //设置mapper命名方式
//        globalConfig.setMapperName("%sDao");
//        //设置mapper.xml命名方式
//        globalConfig.setXmlName("%sMapper");
//        //设置service命名
//        globalConfig.setServiceName("%sService");
//        //设置serviceImpl命名
//        globalConfig.setServiceImplName("%sServiceImpl");
//        return globalConfig;
//    }
//    //生成策略配置
//    private static StrategyConfig strategyConfig() {
//        StrategyConfig strategyConfig = new StrategyConfig();
//        //是否跳过视图
//        strategyConfig.setSkipView(true);
//        //数据库表映射到实体的命名策略
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        //是否为lombok模型（默认 false）
//        strategyConfig.setEntityLombokModel(true);
//        //生成 @RestController 控制器(默认false)
//        strategyConfig.setRestControllerStyle(true);
//        //设置包含的表，没有默认扫描全表
//        strategyConfig.setInclude(scanner("表名"));
//        //自定义继承的Entity类全称，带包名
////        strategyConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        //自定义继承的Controller类全称，带包名
////        strategyConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        return strategyConfig;
//    }
//    //数据源配置
//    private static DataSourceConfig dataSourceConfig() {
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/zhkz?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL");
//        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("root");
//        return dataSourceConfig;
//    }
//    //包配置
//    private static PackageConfig packageConfig() {
//        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName(scanner("模块名"));
//        packageConfig.setParent("cn.zhkz");
//        return packageConfig;
//    }
//}
