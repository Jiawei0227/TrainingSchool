package nju.wjw.controller;

import nju.wjw.service.StudentService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Controller
public class LoginController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/student/studentLogin")
    public ModelAndView studentLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/student-Login", method = RequestMethod.POST)
    public ModelAndView studentLoginPost(HttpSession session, String username, String password){
        ResultMsg re = studentService.studentLogin(username,password);
        if(re.getState() == StateCode.SUCCESS){
            session.setAttribute("studentVO",re.getInfo());
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("redirect:/student/studentCardService");
            return modelAndView;
        }else{
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            ModelAndView modelAndView = new ModelAndView("loginInfo",data);
            return modelAndView;
        }
    }

    @RequestMapping("/student/studentRegister")
    public ModelAndView studentRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentRegister");
        return modelAndView;
    }

    @RequestMapping(value = "/student-register",method = RequestMethod.POST)
    public ModelAndView studentRegisterPost(String name, String age,String password, String email){

        StudentVO s = new StudentVO();
        s.name = name;
        s.age = age;
        s.email = email;
        s.password = password;

        ResultMsg re = studentService.studentRegister(s);
        if(re.getState() == StateCode.SUCCESS){
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            ModelAndView modelAndView = new ModelAndView("registerInfo",data);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentRegister");
        return modelAndView;
    }

    @RequestMapping(value = "/student/studentLogout")
    public String studentLogOut(HttpSession session){
        session.invalidate();
        return "studentLogin";
    }

}
