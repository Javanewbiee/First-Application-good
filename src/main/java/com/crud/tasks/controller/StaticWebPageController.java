package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object>model) {
        model.put("variable", "My thymeleaf variable");
        model.put("multiply"," * ");
        model.put("two",2);
        model.put("add"," + ");
        model.put("equals"," = ");
        model.put("minus"," - ");
        return "index";
    }
}
