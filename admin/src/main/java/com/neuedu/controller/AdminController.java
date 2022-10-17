package com.neuedu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 施子安
 * @create
 */
@RestController
public class AdminController {
    @GetMapping("/index")
    String index(){
        return  "admin-index";
    }
}
