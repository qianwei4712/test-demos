package java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author shiva   2020/4/21 21:48
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user = null;
        OptionalTest.test2(user);
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
            System.out.println("”√ªß¥Ê‘⁄");
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
