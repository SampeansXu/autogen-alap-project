package ${packageName}.config.mybatisplus;

import com.baomidou.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MybatisPlusConfig {


    /**
     * 主从自动切换插件
     *
     * @return
     */
    @Bean
    public MasterSlaveAutoRoutingPlugin masterSlaveAutoRoutingPlugin() {
        return new MasterSlaveAutoRoutingPlugin();
    }

    /**
     * 分页拦截器
     *
     * @param
     * @return
     */
    @Bean
    PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }


    /**
     * 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
