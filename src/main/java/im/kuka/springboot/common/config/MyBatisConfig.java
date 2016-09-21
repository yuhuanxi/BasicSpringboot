/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package im.kuka.springboot.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午9:19
 * @version: 1.0
 * @since: 1.0
 * @description: Mybatis 基础配置
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    private static final Logger LOG = LogManager.getLogger(MyBatisConfig.class);

    @Autowired
    @Qualifier("devDataSource")
    DataSource devDataSource;   //结合 Qualifier 注解,让其按名称来匹配

    @Autowired
    @Qualifier("prodDataSource")
    DataSource prodDataSource;

    // 配置开发数据源
    @Bean(name = "devDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dev", locations = "classpath:/config/datasource.yml")
    public DataSource devDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 配置生产数据源
    @Bean(name = "prodDataSource")
    @Primary // 在配置多个数据源时,一定要加上该注解
    @ConfigurationProperties(prefix = "spring.datasource.prod", locations = "classpath:/config/datasource.yml")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 配置开发环境 sqlSessionFactory
    @Bean(name = "devSqlSessionFactory")
    public SqlSessionFactory devSqlSessionFactoryBean(@Qualifier("devDataSource") DataSource dataSource) throws SQLException {
        return getSqlSessionFactory(dataSource);
    }

    // 配置生产环境 sqlSessionFactory
    @Bean(name = "prodSqlSessionFactory")
    public SqlSessionFactory prodSqlSessionFactoryBean(@Qualifier("prodDataSource") DataSource dataSource) throws SQLException {
        return getSqlSessionFactory(dataSource);
    }

    private SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("im.kuka.springboot.demo.model");// 多个package用,隔开
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Transaction 相关配置
     * 因为有两个数据源，所有使用ChainedTransactionManager把两个DataSourceTransactionManager包括在一起。
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dev = new DataSourceTransactionManager(devDataSource);
        DataSourceTransactionManager prod = new DataSourceTransactionManager(prodDataSource);
        return new ChainedTransactionManager(dev, prod);
    }
}
