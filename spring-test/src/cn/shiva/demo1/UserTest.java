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
        // ���� spring �����ļ�
        ApplicationContext context = new ClassPathXmlApplicationContext("resources/User.xml");
        //��ȡ����
        User user = context.getBean("user", User.class);
        user.test();
    }
}
