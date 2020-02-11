package cn.edu.seu.mp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xuejing
 * @CreateTime: 2020-01-19 23:08
 * @Description: mybatis-plus分页插件
 */

//@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        //paginationInterceptor.setOverflow(false);
        //设置最大单页限制数量，默认 500 条，-1 不受限制
        //paginationInterceptor.setLimit(3);
        //开启 count 的 join 优化,只针对部分 left join
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize());
        return paginationInterceptor;
    }
}


