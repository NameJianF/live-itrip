package live.itrip.admin.cache;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by Feng on 2016/7/18.
 */
public class RedisCacheClient {

    public static void flushDB() {
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) RedisCache.getJedisConnectionFactory().getConnection();
            connection.flushDb();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public static void flushAll() {
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) RedisCache.getJedisConnectionFactory().getConnection();
            connection.flushAll();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Object getObject(Object key) {
        Object result = null;
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) RedisCache.getJedisConnectionFactory().getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = serializer.deserialize(connection.get(serializer.serialize(key)));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public static int getSize() {
        int result = 0;
        RedisConnection connection = null;
        try {
            connection = RedisCache.getJedisConnectionFactory().getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public static void putObject(Object key, Object value) {
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) RedisCache.getJedisConnectionFactory().getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Object removeObject(Object key) {
        JedisConnection connection = null;
        Object result = null;
        try {
            connection = (JedisConnection) RedisCache.getJedisConnectionFactory().getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = connection.expire(serializer.serialize(key), 0);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

}
