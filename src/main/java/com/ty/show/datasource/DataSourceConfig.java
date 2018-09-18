package com.ty.show.datasource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * @author zyc
 */
@Configuration
@EnableScheduling
@Slf4j
public class DataSourceConfig {
    /**
     * api DataSource
     *
     * @return data source
     * @Primary 注解用于标识默认使用的 DataSource Bean，因为有多个 DataSource Bean，该注解可用于 api
     * 或 cloud DataSource Bean, 但不能用于 dynamicDataSource Bean, 否则会产生循环调用
     * @ConfigurationProperties 注解用于从 application.properties 或 application.yml 文件中读取配置，为 Bean 设置属性
     */
    @Bean("api")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.api")
    public DataSource api() {
        return DataSourceBuilder.create().build();
    }

    /**
     * cloud DataSource * * @return data source
     */
    @Bean("base")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource base() {
        return DataSourceBuilder.create().build();
    }


//    /**
//     * hiwatch DataSource * * @return data source
//     */
//    @Bean("hiwatch")
//    @ConfigurationProperties(prefix = "spring.datasource.xcx")
//    public DataSource hiwatch() {
//        return DataSourceBuilder.create().build();
//    }

    /**
     * Dynamic data source. * * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicRoutingDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("api", api());
        dataSourceMap.put("base", base());
//        dataSourceMap.put("hiwatch", hiwatch());
        // 将 api 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(api());
        // 将  api 和 base 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        return dynamicRoutingDataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     *
     * @return the sql session factory bean
     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     *
     */

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }
}
