package cl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("data", "SpringBoot 成功集成 Thymeleaf 模版！");
        return "index";
    }
}
