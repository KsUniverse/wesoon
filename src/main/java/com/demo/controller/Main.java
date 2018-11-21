package com.demo.controller;


import com.wxy.Check;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Main {
    @RequestMapping("/main")
    public List<String> main() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list = null;
        Check.that(list).isNotNull("X11");
        return list;
    }
}
