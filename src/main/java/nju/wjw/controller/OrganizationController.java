package nju.wjw.controller;

import nju.wjw.service.OrganizationService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.CourseVO;
import nju.wjw.vo.OrganizationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

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
}
