package live.itrip.redis.service;

import live.itrip.common.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Created by Feng on 2016/6/30.
 * redis 操作
 */
public class JedisClientSingleImpl implements JedisClient {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 根据缓存键获取Redis缓存中的值
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        String value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    /**
     * 根据缓存键获取Redis缓存中的值
     *
     * @param key
     * @return
     */
    @Override
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));

            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    /**
     * 保存一个对象到redis中
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.set(key, value);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key
     * @param value
     * @param expire 过去时间
     * @return
     */
    @Override
    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    /**
     * 保存一个对象到redis中
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String v = null;
        try {
            v = jedis.set(key, value);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return v;
    }

    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    @Override
    public String set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        String v = null;
        try {
            v = jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return v;
    }

    /**
     * 从指定hash中拿一个数据
     *
     * @param hkey
     * @param key
     * @return
     */
    @Override
    public String hget(String hkey, String key) {
        String value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.hget(hkey, key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    /**
     * 添加一个内容到指定key的hash中
     *
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.hset(hkey, key, value);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 名称为key的string增1操作
     *
     * @param key
     * @return
     */
    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.incr(key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置 key 的过期时间
     *
     * @param key
     * @param second
     * @return
     */
    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.expire(key, second);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置 key 的过期时间
     *
     * @param key
     * @param second
     * @return
     */
    @Override
    public long expire(byte[] key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.expire(key, second);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 获得一个key的有效时间
     *
     * @param key
     * @return
     */
    @Override
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.ttl(key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据缓存键清除Redis缓存中的值
     *
     * @param key
     * @return
     */
    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据缓存键清除Redis缓存中的值
     *
     * @param key
     * @return
     */
    @Override
    public long del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * @param hkey
     * @param key
     * @return
     */
    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.hdel(hkey, key);
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取缓存中所有符合pattern的key
     *
     * @param pattern
     * @return
     */
    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            return null;
        }
        try {
            keys = jedis.keys(pattern.getBytes());
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return keys;
    }

    /**
     * 删除当前选择数据库中的所有key
     */
    @Override
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取redis db的大小
     *
     * @return
     */
    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } catch (Exception e) {
            Logger.error("Redis Error:", new Throwable(e));
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return dbSize;
    }
}
