package cn.edu.seu.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * SpringBoot应用启动类
 */

@SpringBootApplication
@MapperScan("cn.edu.seu.mp") //设置mapper接口的扫描包
public class SpringbootTest3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTest3Application.class, args);
    }

}
