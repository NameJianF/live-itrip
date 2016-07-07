package live.itrip.redis.service.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.data.redis.cache.RedisCache;

import java.util.Collection;

/**
 * Created by Feng on 2016/7/7.
 * <p>
 * 继承了 spring 的 AbstractCacheManager 管理 RedisCache 类缓存管理
 */
public class CacheManager<T extends Object> extends AbstractCacheManager {
    private Collection<? extends RedisCache> caches;

    public void setCaches(Collection<? extends RedisCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }
}
