import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
//        RedisCache redisCache = new RedisCache("0");
//        redisCache.putObject("feng123456", "123456");
//        Object object = redisCache.getObject("feng123456");
//        System.err.println(object.toString());
//        int size = redisCache.getSize();
//        System.err.println(size);
//        redisCache.removeObject("feng123456");
//        object = redisCache.getObject("feng123456");
//        System.err.println(object.toString());
//        size = redisCache.getSize();
//        System.err.println(size);

    }
}
