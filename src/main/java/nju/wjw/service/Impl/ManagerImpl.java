package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.Course;
import nju.wjw.entity.Manager;
import nju.wjw.service.ManagerService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.CourseVO;
import nju.wjw.vo.ManagerVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jerry Wang on 2017/3/9.
 */
@Repository
public class ManagerImpl implements ManagerService {


    public ResultMsg login(String username, String password) {
        int cardId = Integer.parseInt(username);
        try {
            Manager ss = DAOManager.managerDao.get(cardId);
            if (ss.getPassword().equals(password)) {

                ManagerVO m = new ManagerVO();
                m.cardNum = ss.toCardFormat();
                m.password = ss.getPassword();
                m.name = ss.getName();
                return new ResultMsg(StateCode.SUCCESS, m);
            } else
                return new ResultMsg(StateCode.FAILURE, "您输入的密码有误，请尝试重新输入");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.LOGINERROR,"账号输入异常，请检查您的账号及密码");
        }
    }

    public List<CourseVO> getUnCheckedCourse() {
        List<Course> courses = DAOManager.courseDao.getUnCheckedList();
        if(courses==null||courses.isEmpty())
            return null;

        return courses.stream().map(e->{
            CourseVO c = new CourseVO();
            c.name = e.getName();
            c.price = e.getPrice()+"";
            c.startTime = e.getStartTime().toString();
            c.endTime = e.getEndTime().toString();
            c.id = e.getCid()+"";
            c.description = e.getDescription();
            c.teacher = e.getTeacher();
            c.isPassed = e.getIsPassed();
            c.isChecked = e.getIsChecked();
            return c;
        }).collect(Collectors.toList());
    }

    @Override
    public CourseVO getCourse(String id) {
        Course c = DAOManager.courseDao.get(Integer.parseInt(id));
        CourseVO courseVO = new CourseVO();
        courseVO.id = c.getCid()+"";
        courseVO.name = c.getName();
        courseVO.price = c.getPrice()+"";
        courseVO.startTime = c.getStartTime().toString();
        courseVO.endTime = c.getEndTime().toString();
        courseVO.description = c.getDescription();
        courseVO.teacher = c.getTeacher();
        courseVO.isChecked = c.getIsChecked();
        courseVO.isPassed = c.getIsPassed();

        return courseVO;
    }

    @Override
    public ResultMsg setCourseApply(String id, int isPassed) {
        Course c = DAOManager.courseDao.get(Integer.parseInt(id));
        if(c.getIsChecked()==0) {
            c.setIsChecked(1);
            c.setIsPassed(isPassed);
            DAOManager.courseDao.update(c);
            return new ResultMsg(StateCode.SUCCESS,"审核成功");
        }else{
            return new ResultMsg(StateCode.FAILURE,"该课程已被审核处理，请勿重复处理");
        }

    }
}
