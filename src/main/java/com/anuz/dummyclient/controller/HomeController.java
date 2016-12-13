/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anuz.dummyclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author anuz
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/test")
    public @ResponseBody String test(){
        return "hello";
    }
}
