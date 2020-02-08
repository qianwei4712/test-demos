package cn.shiva.datasources.controller;

import cn.shiva.datasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author shiva   2020/2/7 18:27
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "test")
    public String test(){

        String user = userService.getUser();
        String user2 = userService.getUser2();

        return "123";
    }




}
