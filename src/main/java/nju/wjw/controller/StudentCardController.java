package nju.wjw.controller;

import nju.wjw.service.StudentCardService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.StudentCardVO;
import nju.wjw.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Jerry Wang on 2017/2/15.
 */
@Controller
public class StudentCardController {

    @Autowired
    StudentCardService studentCardService;

    @RequestMapping(value = "/student/studentCardService")
    public String studentCardService(){
        return "studentCardService";
    }

    @RequestMapping(value = "/student/studentCardService/studentActive")
    public String studentAcitve(){
        return "studentActive";
    }

    @RequestMapping(value = "/student/student-active",method = RequestMethod.POST)
    public ModelAndView studentActivePost(HttpSession session,String account, String password, String money){
        StudentVO student = (StudentVO)session.getAttribute("studentVO");
        ResultMsg re = studentCardService.studentActive(account,password,money,student.studentID);
        ModelAndView md = null;
        if(re.getState()== StateCode.SUCCESS){
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            data.put("description","操作成功");
            student.balance+=Double.parseDouble(money);
            md = new ModelAndView("info",data);
        }else{
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            data.put("description","操作失败");
            md = new ModelAndView("info",data);
        }
        return md;
    }

    @RequestMapping(value = "/student/studentCardInvalid")
    public ModelAndView studentCardMemberValid(HttpSession session){
        StudentVO student = (StudentVO)session.getAttribute("studentVO");
        HashMap<String,Object> data = new HashMap<String, Object>();
        StudentCardVO re = studentCardService.studentCardVO(student.studentID);
        data.put("MemberValidity",re.memberValidity);
        data.put("Time",re.time);
        data.put("description","身份取消");
        ModelAndView md = new ModelAndView("studentCardInvalid",data);
        return md;
    }

    @RequestMapping(value = "/student/studentCard-Invalid",method = RequestMethod.POST)
    public ModelAndView studentCardInvalid(HttpSession session){
        StudentVO student = (StudentVO)session.getAttribute("studentVO");
        ResultMsg re =studentCardService.studentCardInvalid(student.studentCardID);
        ModelAndView md = null;
        if(re.getState() == StateCode.SUCCESS){
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            data.put("description","会员资格取消");
            md = new ModelAndView("info",data);
        }
        return md;
    }

    @RequestMapping(value = "/student/studentCard-Valid",method = RequestMethod.POST)
    public ModelAndView studentCardvalid(HttpSession session){
        StudentVO student = (StudentVO)session.getAttribute("studentVO");
        ResultMsg re =studentCardService.studentCardValid(student.studentCardID);
        ModelAndView md = null;
        if(re.getState() == StateCode.SUCCESS){
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            data.put("description","会员资格恢复");
            md = new ModelAndView("info",data);
        }
        return md;
    }

    @RequestMapping(value="/student/studentCardValid")
    public ModelAndView studentCardValid(HttpSession session){
        StudentVO student = (StudentVO)session.getAttribute("studentVO");
        HashMap<String,Object> data = new HashMap<String, Object>();
        StudentCardVO re = studentCardService.studentCardVO(student.studentID);
        data.put("MemberValidity",re.memberValidity);
        data.put("Time",re.time);
        data.put("description","会员卡资格恢复");
        ModelAndView md = new ModelAndView("studentCardInvalid",data);
        return md;
    }


}
