package com.jd.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Servlet;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控
    @Bean
    public ServletRegistrationBean<Servlet> druidServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //后台要有人登陆，账号密码配置
        HashMap<String,String> initparamaters = new HashMap<>();

        //增加配置
        initparamaters.put("loginUsername","admin");//登陆的key是固定的 loginUsername loginPassword
        initparamaters.put("loginPassword","123456");

        //允许谁可以访问
        initparamaters.put("allow",""); //空代表所有人可以访问

        servletRegistrationBean.setInitParameters(initparamaters);//设置初始化参数

        return servletRegistrationBean;
    }

}
