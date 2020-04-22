package java8;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author shiva   2020/4/21 21:48
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user = new User();
        user.setNo("9999");
        OptionalTest.test5(user);
    }




    public static void test5(User user){
        Optional<User> opt = Optional.ofNullable(user);
        Optional<String> s = opt.map(user1 -> user.getNo());
        s.ifPresent(s1 -> System.out.println(s1));

        Optional.ofNullable(user).map(User::getNo).ifPresent(s1 -> {
            //业务逻辑
            System.out.println(s1);
        });

        if (user != null && StringUtils.isNotBlank(user.getNo())){
            //业务逻辑
            System.out.println(user.getNo());
        }

    }


    public static void test4(User user){
        Optional<User> opt = Optional.ofNullable(user);
        opt.orElseGet(() -> {
            User user1 = new User();
            user1.setNo("2");
            return user1;
        });
    }

    public static void test3(User user){
        Optional<User> opt = Optional.ofNullable(user);
        user = opt.orElse(new User());
    }

    public static void test2(User user){
        Optional<User> opt = Optional.ofNullable(user);
        opt.ifPresent(u -> {
            System.out.println("用户存在");
            System.out.println(u.getNo());
        });
    }

    public static void test1(){
        Optional<Object> optional = Optional.empty();
    }

    static class User{
        private String no;
        public String getNo() {
            return no;
        }
        public void setNo(String no) {
            this.no = no;
        }
    }

}
