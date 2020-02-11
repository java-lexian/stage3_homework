package cn.edu.seu.mp.autogenerator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xuejing
 * @CreateTime: 2020-01-19 19:48
 * @Description: mybatis-plus 代码生成器
 */
public class CodeGenerator {

    /**
     * 包名（在此包名位置下生成代码）
     */
    private static String packageParent = "cn.edu.seu.mp";

    /**
     * 创建者
     */
    private static String author = "xuejing";

    /**
     * 数据源驱动名称
     */
    private static String dataSourceDriverName = "com.mysql.jdbc.Driver";

    /**
     * 驱动连接的URL
     */
    private static String dataSourceUrl = "jdbc:mysql://localhost:3306/mytest" +
            "?useUnicode=true&useSSL=false&characterEncoding=utf8";

    /**
     * 数据库连接用户名
     */
    private static String dataSourceUserName = "root";

    /**
     * 数据库连接密码
     */
    private static String dataSourcePassword = "123456";

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setActiveRecord(true);
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceUrl);
        dsc.setDriverName(dataSourceDriverName);
        dsc.setUsername(dataSourceUserName);
        dsc.setPassword(dataSourcePassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //读取模块名
        pc.setModuleName(scanner("模块名"));
        pc.setParent(packageParent);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置字段名和表名是否把下划线换成驼峰命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否启用lombok
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);

        //设置要生成哪些表，如果不设置就是生成所有的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);

        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setStrategy(strategy);
        mpg.execute();
    }


    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
