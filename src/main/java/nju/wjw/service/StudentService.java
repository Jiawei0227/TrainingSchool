package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.*;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/12.
 */
public interface StudentService {

    /**
     * 学员卡注册
     * @param student
     * @return
     */
    public ResultMsg studentRegister(StudentVO student);

    /**
     * 验证登录情况，账户密码是否正确
     * @param cardNo
     * @param password
     * @return
     */
    public ResultMsg studentLogin(String cardNo,String password);

    /**
     * 返回可选课程列表
     * @return
     */
    public List<CourseVO> getAllCourses();

    /**
     * 根据课程id获取课程详细信息
     * @param id
     * @return
     */
    public CourseDetailViewVO getCoursesDetail(String id, String studentId);

    /**
     * 学生选课
     * @param cid
     * @param sid
     * @return
     */
    public ResultMsg addCourse(String cid,String sid);

    /**
     * 获取学生所选的所有课程
     * @param sid
     * @return
     */
    public List<StudentCourseVO> getStudentCourse(String sid);

    /**
     * 获取学生历史列表
     * @param sid
     * @return
     */
    public List<HistoryVO> getHistory(String sid);

}
