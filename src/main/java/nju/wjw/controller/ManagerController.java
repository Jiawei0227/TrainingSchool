package nju.wjw.controller;

import nju.wjw.service.ManagerService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/3/10.
 */
@Controller
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/managerPlatform/managerService")
    public ModelAndView managerService(){
        return new ModelAndView("managerService");
    }

    @RequestMapping("/managerPlatform/CheckApply")
    public ModelAndView checkApplyService(){
        List<CourseVO> courseVOList = managerService.getUnCheckedCourse();
        ModelAndView modelAndView = new ModelAndView("ApplyCheck");
        modelAndView.addObject("courseList",courseVOList);
        return modelAndView;
    }

    @RequestMapping("/managerPlatform/applyCheckCourseDetail")
    public ModelAndView checkApplySercviceDetail(String id){

        CourseVO courseVO = managerService.getCourse(id);
        if(courseVO.isChecked==1) {
            ModelAndView m =new ModelAndView("info");
            m.addObject("info","该课程已经被审核,请勿重复审核");
            return m;
        }

        ModelAndView m = new ModelAndView("applyCheckCourseDetail");
        m.addObject("courseVO",courseVO);
        return m;

    }

    @RequestMapping(value = "/managerPlatform/courseCheck/success" ,method = RequestMethod.POST)
    public ModelAndView courseSuccess(String id){
        System.out.println(id+"ffafada2222222222222222111111111");
        ResultMsg re = managerService.setCourseApply(id,1);
        if(re.getState()== StateCode.SUCCESS){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/managerPlatform/CheckApply");
            return modelAndView;
        }else{
            ModelAndView m = new ModelAndView("info");
            m.addObject("info",re.getInfo());
            return m;
        }
    }

    @RequestMapping(value = "/managerPlatform/courseCheck/failure" , method = RequestMethod.POST)
    public ModelAndView courseFailure(String id){
        ResultMsg re = managerService.setCourseApply(id,0);
        if(re.getState()== StateCode.SUCCESS){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/managerPlatform/CheckApply");
            return modelAndView;
        }else{
            ModelAndView m = new ModelAndView("info");
            m.addObject("info",re.getInfo());
            return m;
        }
    }
}
