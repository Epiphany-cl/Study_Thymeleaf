package cl.controller;

import cl.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    //http://localhost:8080/thymeleaf01/thymeleaf/index
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("data", "SpringBoot 成功集成 Thymeleaf 模版！");
        return "index";
    }

    //http://localhost:8080/thymeleaf01/thymeleaf/user
    @RequestMapping(value = "/user")
    public String userShow(Model model) {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPhone("13333333333");
        user.setAddress("北京市海淀区");
        user.setBirthday(new Date());
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping("/info")
    public String info(Model model) {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPhone("13333333333");
        user.setAddress("北京市海淀区");
        user.setBirthday(new Date());
        model.addAttribute("user", user);
        return "url";
    }

    @RequestMapping("/userList")
    public String userList(Model model) {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三" + i);
            user.setPhone("13333333333" + i);
            user.setAddress("北京市海淀区" + i);
            user.setBirthday(new Date());
            list.add(user);
        }
        model.addAttribute("list", list);
        return "userList";
    }

    @RequestMapping("/userMap")
    public String userMap(Model model) {
        HashMap<Integer, User> map = new HashMap<>();
        for (int i = 1; i < 9; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三" + i);
            user.setPhone("13333333333" + i);
            user.setAddress("北京市海淀区" + i);
            user.setBirthday(new Date());
            map.put(i, user);
        }
        model.addAttribute("map", map);
        return "userMap";
    }
}
