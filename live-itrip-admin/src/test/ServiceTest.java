import com.alibaba.fastjson.JSON;
import live.itrip.admin.dao.UserMapper;
import live.itrip.admin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Test
//    public void testUuid() {
//        User user = userMapper.selectByUserName("fjf789@126.com");
//        System.err.println(JSON.toJSON(user));
//    }
}
