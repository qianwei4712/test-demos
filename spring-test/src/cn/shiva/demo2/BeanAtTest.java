package cn.shiva.demo2;

import cn.shiva.demo1.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class BeanAtTest {

    @Resource
    private Users users;

    @Test
    public void test1(){
        // 加载 spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("resources/beanAt.xml");
        //获取对象
        Users user = context.getBean("users", Users.class);
        user.test();
    }
}
