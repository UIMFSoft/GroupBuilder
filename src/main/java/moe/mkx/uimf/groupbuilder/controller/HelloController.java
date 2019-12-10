package moe.mkx.uimf.groupbuilder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hey")
    public String heyFromUrl() {
        return "Hello world!";
    }
}
