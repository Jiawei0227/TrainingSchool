package nju.wjw.controller;

import nju.wjw.service.StudentService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.CourseDetailViewVO;
import nju.wjw.vo.HistoryVO;
import nju.wjw.vo.StudentCourseVO;
import nju.wjw.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jerry Wang on 2017/3/14.
 */
@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/student/studentService")
    public ModelAndView studentService(){
        return new ModelAndView("studentService");
    }

    @RequestMapping("/student/courseList")
    public ModelAndView courseList(){
        ModelAndView model = new ModelAndView("courseLists");

        model.addObject("courseList",studentService.getAllCourses());

        return model;
    }

    @RequestMapping("/student/courseDetail")
    public ModelAndView courseDetail(HttpSession session, String id){
        ModelAndView modelAndView = new ModelAndView("courseDetail");
        StudentVO stuVO = (StudentVO)session.getAttribute("studentVO");
        String stuId = stuVO.studentID;
        CourseDetailViewVO re = studentService.getCoursesDetail(id,stuId);
        modelAndView.addObject("courseDetailViewVO",re);

        return modelAndView;
    }

    @RequestMapping(value = "/student/addCourse",method = RequestMethod.POST)
    public ModelAndView courseApply(HttpSession session, String cid){
        StudentVO studentVO = (StudentVO)session.getAttribute("studentVO");
        ResultMsg re = studentService.addCourse(cid,studentVO.studentID);
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

    @RequestMapping("/student/myCourseList")
    public ModelAndView myCourseList(HttpSession session){
        StudentVO studentVO = (StudentVO)session.getAttribute("studentVO");
        List<StudentCourseVO> courseList = studentService.getStudentCourse(studentVO.studentID);
        ModelAndView modelAndView = new ModelAndView("myCourseList");
        modelAndView.addObject("courseList",courseList);
        return modelAndView;
    }

    @RequestMapping("/student/myHistory")
    public ModelAndView myHistoryList(HttpSession session){
        StudentVO studentVO = (StudentVO) session.getAttribute("studentVO");
        List<HistoryVO> historyVOs = studentService.getHistory(studentVO.studentID);
        ModelAndView modelAndView = new ModelAndView("studentHistory");
        modelAndView.addObject("historyVO",historyVOs);
        return modelAndView;
    }

}
