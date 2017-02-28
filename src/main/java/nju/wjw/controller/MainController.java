package nju.wjw.controller;

import nju.wjw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jerry Wang on 2017/2/12.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private StudentService userService;

    @RequestMapping("")
    public String index(){
        return "index";
    }


}
