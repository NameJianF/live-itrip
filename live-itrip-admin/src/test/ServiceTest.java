import live.itrip.sso.rpc.service.RpcSsoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Feng on 2016/7/13.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml"})
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


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:dubbo/consumer-sso.xml"});
        context.start();
        RpcSsoService ssoService = (RpcSsoService) context.getBean("rpcSsoService");
        System.out.println(ssoService.login("fjf789@126.com", "123456", "", "", "", ""));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
