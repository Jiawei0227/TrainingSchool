package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.*;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/3/6.
 */
public interface OrganizationService {

    /**
     * 企业机构注册
     * @param organzationVO
     * @return
     */
    public ResultMsg organizationRegister(OrganizationVO organzationVO);

    /**
     * 验证登录情况，账户密码是否正确
     * @param cardNo
     * @param password
     * @return
     */
    public ResultMsg organizationLogin(String cardNo,String password);

    /**
     * 创建一个新的课程申请
     * @param courseVO
     * @return
     */
    public ResultMsg newCourseApply(CourseVO courseVO, String oid);

    /**
     * 根据机构编号获取所有的课程列表
     * @param organizationId
     * @return
     */
    public List<OrganCourseVO> getCourseList(String organizationId);

    /**
     * 获取课程确认信息
     * @param cid
     * @return
     */
    public CourseConfirmDetailViewVO getCourseConfirm(String cid);

    /**
     * 学生选课是否通过信息
     * @param sid
     * @param cid
     * @param passCode
     * @return
     */
    public ResultMsg CourseConfirmPost(String sid,String cid,String passCode);

    /**
     * 向课程中添加新的学生
     * @return
     */
    public ResultMsg courseAddStudent(StudentVO studentVO,String cid);

    /**
     * 更新课表申请
     * @param courseVO
     * @return
     */
    public ResultMsg updateCourseApply(CourseVO courseVO);

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    public CourseVO getCourseVO(String id);

    /**
     * 获取机构历史
     * @param oid
     * @return
     */
    public List<HistoryVO> getHistroy(String oid);

    /**
     * 获取某一课程的成绩列表
     * @param cid
     * @return
     */
    public List<StudentScoreVO> getScore(String cid);

    /**
     * 填写学生成绩
     * @return
     */
    public ResultMsg setScores(List<StudentScoreVO> studentScoreVOs);
}
