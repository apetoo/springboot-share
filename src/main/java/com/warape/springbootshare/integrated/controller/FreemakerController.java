package com.warape.springbootshare.integrated.controller;

import com.warape.springbootshare.integrated.properties.JedisClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot-share
 * @description: freemark controller
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 15:50
 **/
@Controller
public class FreemakerController {

    @Autowired
    private JedisClientProperties jedisClientProperties;

    @GetMapping("/testFreemaker")
    public String testFreemaker(HttpServletRequest request){
        request.setAttribute("jedisconf",jedisClientProperties);
        return "demo";
    }
}
