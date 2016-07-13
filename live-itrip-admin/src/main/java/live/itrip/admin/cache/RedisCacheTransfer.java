package live.itrip.admin.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by Feng on 2016/7/13.
 * 静态注入中间类
 */
public class RedisCacheTransfer {
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}
