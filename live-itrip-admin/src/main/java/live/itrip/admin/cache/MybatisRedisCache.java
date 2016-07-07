package live.itrip.admin.cache;

import live.itrip.redis.service.cache.RedisCache;
import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * Created by Feng on 2016/7/7.
 * <p>
 * 实现Redis 缓存
 */
public class MybatisRedisCache extends LoggingCache {
    public MybatisRedisCache(String id) {
        super(new RedisCache(id));
    }
}
