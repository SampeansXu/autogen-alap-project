package ${packageName}.config.cache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMethodCache(basePackages = "${packageName}")
@EnableCreateCacheAnnotation
public class CacheConfig {
}
