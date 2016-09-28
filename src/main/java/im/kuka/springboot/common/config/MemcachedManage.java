package im.kuka.springboot.common.config;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author: shipeng.yu
 * @time: 2016年09月28日 下午9:32
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Configuration
@ConfigurationProperties(locations = "classpath:/memcached.yml", prefix = "memcached")
// 这里可以使用 ConfigurationProperties,也可以使用 PropertySource ,如果使用 PropertySource,则需要配置 Value
//@PropertySource("classpath:memcached.yml")
public class MemcachedManage {

    private static final Logger LOG = LogManager.getLogger(MemcachedManage.class);

    //    @Value("${memcached.address}")
    private String address;

    public CacheFactory cacheFactory() throws Exception {
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheClientFactory(memcacheClientFactory());
        cacheFactory.setConfiguration(cacheConfiguration());
        cacheFactory.setAddressProvider(defaultAddressProvider());
        cacheFactory.setCacheName("dev");  // 名称必填
        cacheFactory.afterPropertiesSet();
        return cacheFactory;
    }

    @Bean(name = "cache")
    public Cache cache() throws Exception {
        try {
            Cache cache = cacheFactory().getCache();
            if (cache == null) {
                cache = cacheFactory().getObject();
            }
            return cache;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    public DefaultAddressProvider defaultAddressProvider() {
        DefaultAddressProvider defaultAddressProvider = new DefaultAddressProvider();
        defaultAddressProvider.setAddress(address);
        return defaultAddressProvider;
    }

    public MemcacheClientFactoryImpl memcacheClientFactory() {
        MemcacheClientFactoryImpl memcacheClientFactory = new MemcacheClientFactoryImpl();
        try {
            memcacheClientFactory.create(defaultAddressProvider().getAddresses(), cacheConfiguration());
            return memcacheClientFactory;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public CacheConfiguration cacheConfiguration() {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setConsistentHashing(true);
        return cacheConfiguration;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
