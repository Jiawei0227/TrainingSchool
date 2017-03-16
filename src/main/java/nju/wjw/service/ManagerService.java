package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.CourseVO;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/3/9.
 */
public interface ManagerService {

    public ResultMsg login(String username,String password);

    public List<CourseVO> getUnCheckedCourse();

    public CourseVO getCourse(String id);

    public ResultMsg setCourseApply(String id,int isPassed);

}
