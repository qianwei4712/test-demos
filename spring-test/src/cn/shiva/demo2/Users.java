package cn.shiva.demo2;

import org.springframework.stereotype.Component;

/**
 * @author shiva   2020/6/27 13:09
 */
@Component
public class Users {
    private String s;

    private String ss;

    public void test(){
        System.out.println("123456aaa7");
        System.out.println(s);
    }

    public Users() {
    }

    public Users(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }


}
