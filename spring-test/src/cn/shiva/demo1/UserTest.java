package cn.shiva.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shiva   2020/6/27 13:10
 */
public class UserTest {

    @Test
    public void test1(){
        // 加载 spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("resources/User.xml");
        //获取对象
        User user = context.getBean("user", User.class);
        user.test();
    }
}
