package live.itrip.redis.service.cache;

import live.itrip.common.Logger;
import live.itrip.common.util.SerializeUtils;
import live.itrip.redis.service.JedisClientSingleImpl;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Feng on 2016/7/7.
 * <p>
 * Mybatis 接口 实现 redis 功能 类
 */
public class RedisCache implements Cache {
    private String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private JedisClientSingleImpl jedisClientSingle = new JedisClientSingleImpl();

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("必须传入ID");
        }
        Logger.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        return Integer.valueOf(jedisClientSingle.dbSize().toString());
    }

    @Override
    public void putObject(Object key, Object value) {
        Logger.debug("putObject:" + key.hashCode() + "=" + value);
        Logger.info("put to redis sql :" + key.toString());

        try {
            jedisClientSingle.set(SerializeUtils.serialize(key.hashCode()), SerializeUtils.serialize(value));
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    @Override
    public Object getObject(Object key) {
        Object value = null;
        try {
            value = SerializeUtils.unSerialize(jedisClientSingle.get(SerializeUtils.serialize(key.hashCode())));
        } catch (Exception e) {
            Logger.error(e);
        }
        Logger.debug("getObject:" + key.hashCode() + "=" + value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        Object value = null;
        try {
            value = jedisClientSingle.expire(SerializeUtils.serialize(key.hashCode()), 0);
        } catch (JedisConnectionException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.debug("getObject:" + key.hashCode() + "=" + value);
        return value;
    }

    @Override
    public void clear() {
        jedisClientSingle.flushDB();
//            jedisClientSingle.flushAll();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
