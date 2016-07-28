package live.itrip.admin.shiro;

import org.mybatis.caches.redis.RedisCache;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by Feng on 2016/7/28.
 */
public class ShiroRedisManager {
    // 0 - never expire
    private int expire = 0;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            value = jedis.get(key);
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            jedis.del(key);
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
    }

    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            jedis.flushDB();
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = RedisCache.getJedisPool().getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            RedisCache.getJedisPool().returnResource(jedis);
        }
        return keys;
    }
}
