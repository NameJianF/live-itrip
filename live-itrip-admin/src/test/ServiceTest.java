import live.itrip.admin.cache.RedisCache;
import live.itrip.admin.cache.RedisCacheClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Feng on 2016/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml"})
public class ServiceTest {

    //    @Autowired
//    private UserMapper userMapper;
//
    @Test
    public void testUuid() {

        RedisCacheClient.putObject("feng123456", "123456");
        Object object = RedisCacheClient.getObject("feng123456");
        System.err.println(object.toString());
        int size = RedisCacheClient.getSize();
        System.err.println(size);
        RedisCacheClient.removeObject("feng123456");
        object = RedisCacheClient.getObject("feng123456");
        System.err.println(object.toString());
        size = RedisCacheClient.getSize();
        System.err.println(size);

    }
}
