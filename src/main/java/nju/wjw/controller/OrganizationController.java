package nju.wjw.controller;

import nju.wjw.service.OrganizationService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jerry Wang on 2017/3/6.
 */
@Controller
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @RequestMapping(value = "/organization/organizationService")
    public ModelAndView organizationService(){
        return new ModelAndView("organizationService");
    }

    @RequestMapping(value = "/organization/newCourseApply")
    public ModelAndView newCourseApply(){
        return new ModelAndView("newCourseApply");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/newCourse-Apply")
    public ModelAndView newCourseApplyPost(HttpSession session, String name, String description, String teacher, String startTime, String endTime, String price){
        OrganizationVO organizationVO = (OrganizationVO)session.getAttribute("organizationVO");
        CourseVO courseVO = new CourseVO();
        courseVO.name = name;
        courseVO.description = description;
        courseVO.teacher = teacher;
        courseVO.startTime = startTime;
        courseVO.endTime = endTime;
        courseVO.price = price;
        ResultMsg re = organizationService.newCourseApply(courseVO,organizationVO.cardNumber);

        ModelAndView md = null;
        if(re.getState()== StateCode.SUCCESS){
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            data.put("description","操作成功");
            md = new ModelAndView("info",data);
        }else{
            HashMap<String,Object> data = new HashMap<String, Object>();
            data.put("info",re.getInfo());
            data.put("description","操作失败");
            md = new ModelAndView("info",data);
        }
        return md;
    }

    @RequestMapping("/organization/courseConfirm")
    public ModelAndView courseList(HttpSession session){
        OrganizationVO o = (OrganizationVO)session.getAttribute("organizationVO");
        ModelAndView modelAndView = new ModelAndView("courseConfirm");
        modelAndView.addObject("courseList",organizationService.getCourseList(o.id));
        return modelAndView;
    }


    @RequestMapping(value = "/organization/courseConfirmDetail")
    public ModelAndView courseDetailConfirm(String id){
        ModelAndView modelAndView = new ModelAndView("courseConfirmDetail");
        modelAndView.addObject("courseConfirmDetailViewVO",organizationService.getCourseConfirm(id));
        return modelAndView;
    }

    @RequestMapping(value = "/organization/courseConfirmPost",method = RequestMethod.POST)
    public ModelAndView courseConfirmPost(String sid,String cid,String passCode){
        ResultMsg re = organizationService.CourseConfirmPost(sid,cid,passCode);
        ModelAndView md = new ModelAndView("info");
        if(re.getState()== StateCode.SUCCESS){
            md.addObject("info",re.getInfo());
            md.addObject("description","操作成功");
        }else{
            md.addObject("info",re.getInfo());
            md.addObject("description","操作失败");

        }
        return md;
    }

    @RequestMapping(value = "/organization/courseAddStudent",method = RequestMethod.POST)
    public ModelAndView addNewStudentToCourse(String name,String age,String email,String studentCard,String cid){
        StudentVO studentVO = new StudentVO();
        studentVO.name = name;
        studentVO.studentCardID = studentCard;
        studentVO.age = age;
        studentVO.email = email;
        ResultMsg re = organizationService.courseAddStudent(studentVO,cid);
        ModelAndView md = new ModelAndView("info");
        if(re.getState()== StateCode.SUCCESS){
            md.addObject("info",re.getInfo());
            md.addObject("description","操作成功");
        }else{
            md.addObject("info",re.getInfo());
            md.addObject("description","操作失败");

        }
        return md;
    }

    @RequestMapping("/organization/updateCourseList")
    public ModelAndView updateCourseList(HttpSession session){
        OrganizationVO o = (OrganizationVO)session.getAttribute("organizationVO");
        ModelAndView modelAndView = new ModelAndView("courseConfirm");
        modelAndView.addObject("isUpdate",true);
        modelAndView.addObject("courseList",organizationService.getCourseList(o.id));
        return modelAndView;
    }

    @RequestMapping("/organization/updateCourseApplyDetail")
    public ModelAndView updateCourseDetail(String id){
        CourseVO courseVO = organizationService.getCourseVO(id);
        ModelAndView modelAndView = new ModelAndView("updateCourseApply");
        modelAndView.addObject("courseVO",courseVO);
        return modelAndView;
    }

    @RequestMapping(value = "/organization/updateCourseApply",method = RequestMethod.POST)
    public ModelAndView updateCourseApply(String id,String name, String description, String teacher, String startTime, String endTime, String price){
        CourseVO courseVO = new CourseVO();
        courseVO.id = id;
        courseVO.name = name;
        courseVO.description =description;
        courseVO.teacher = teacher;
        courseVO.startTime = startTime;
        courseVO.endTime = endTime;
        courseVO.price = price;

        ResultMsg re = organizationService.updateCourseApply(courseVO);
        ModelAndView md = new ModelAndView("info");
        if(re.getState()== StateCode.SUCCESS){
            md.addObject("info",re.getInfo());
            md.addObject("description","操作成功");
        }else{
            md.addObject("info",re.getInfo());
            md.addObject("description","操作失败");

        }
        return md;
    }

    @RequestMapping("/organization/myHistory")
    public ModelAndView myHistoryList(HttpSession session){
        OrganizationVO organizationVO = (OrganizationVO) session.getAttribute("organizationVO");
        List<HistoryVO> historyVOs = organizationService.getHistroy(organizationVO.id);
        ModelAndView modelAndView = new ModelAndView("organizationHistory");
        modelAndView.addObject("historyVO",historyVOs);
        return modelAndView;
    }

    @RequestMapping("/organization/scoreCheckIn")
    public ModelAndView scoreCheckIn(String id){
        ModelAndView modelAndView = new ModelAndView("scoreCheckInDetail");
        modelAndView.addObject("scoreList",organizationService.getScore(id));
        return  modelAndView;
    }

    @RequestMapping(value = "/organization/scoreCheckInPost",method = RequestMethod.POST)
    public ModelAndView scoreCheckInPost(HttpServletRequest request){
        int i=0;
        List<StudentScoreVO> studentScoreVOs = new ArrayList<>();
        while(request.getAttribute(i+"")!=null){
            StudentScoreVO studentScoreVO = new StudentScoreVO();
            studentScoreVO.sid = (String)request.getAttribute(i+"");
            studentScoreVO.score = (String)request.getAttribute("score"+i);
            studentScoreVO.back = (String)request.getAttribute("back"+i);
            studentScoreVOs.add(studentScoreVO);
            i++;
        }

        return null;
    }
}
