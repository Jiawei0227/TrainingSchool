package nju.wjw.controller;

import nju.wjw.service.ManagerService;
import nju.wjw.service.OrganizationService;
import nju.wjw.service.StudentService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.CourseVO;
import nju.wjw.vo.StudentCourseVO;
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
    @Autowired
    OrganizationService organizationService;
    @Autowired
    StudentService studentService;

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

    @RequestMapping(value="/managerPlatform/organizationList")
    public ModelAndView organizationList(){
        ModelAndView m = new ModelAndView("organizationList");
        m.addObject("organizationList",managerService.getOrganizationList());
        return m;
    }

    @RequestMapping("/managerPlatform/organizationDetail")
    public ModelAndView organizationDetail(String id){
        ModelAndView m = new ModelAndView("organizationDetail");
        m.addObject("organizationVO",managerService.getOrganizationDetail(id));
        return m;
    }

    @RequestMapping(value = "/managerPlatform/checkInAccountPost",method = RequestMethod.POST)
    public ModelAndView checkInAccountPost(String oid){
        ResultMsg re = managerService.checkInAccount(oid);
        ModelAndView md = new ModelAndView("info");
        if(re.getState()== StateCode.SUCCESS){
            md.addObject("info",re.getInfo());
            md.addObject("description","提交成功");
        }else{
            md.addObject("info",re.getInfo());
            md.addObject("description","操作失败");

        }
        return md;
    }

    @RequestMapping("/managerPlatform/courseDetail")
    public ModelAndView courseDetail(String id){
        ModelAndView modelAndView = new ModelAndView("courseConfirmDetail");
        modelAndView.addObject("courseConfirmDetailViewVO",organizationService.getCourseConfirm(id));
        return modelAndView;
    }

    @RequestMapping("/managerPlatform/studentList")
    public ModelAndView studentList(){
        ModelAndView m = new ModelAndView("studentList");
        m.addObject("studentList",managerService.getStudentList());
        return m;
    }

    @RequestMapping("managerPlatform/studentDetail")
    public ModelAndView studentDetail(String id){
        ModelAndView m = new ModelAndView("myCourseList");
        m.addObject("managerAsk","1");
        List<StudentCourseVO> courseList = studentService.getStudentCourse(id);
        m.addObject("courseList",courseList);
        return m;
    }

    @RequestMapping("managerPlatform/studentScore")
    public ModelAndView studentScore(String id){
        ModelAndView modelAndView = new ModelAndView("myScoreList");
        modelAndView.addObject("scoreList",studentService.getMyScore(id));
        modelAndView.addObject("managerAsk","1");
        return modelAndView;
    }

    @RequestMapping("managerPlatform/statistic")
    public ModelAndView statisticView(){
        ModelAndView m = new ModelAndView("statisticView");
        m.addObject("statisticsVO",managerService.getStatistics());
        m.addObject("historyVO",managerService.getHistoryVO());
        return m;
    }


}
