package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.*;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/3/9.
 */
public interface ManagerService {

    public ResultMsg login(String username,String password);

    public List<CourseVO> getUnCheckedCourse();

    public CourseVO getCourse(String id);

    public ResultMsg setCourseApply(String id,int isPassed);

    public List<OrganizationVO> getOrganizationList();

    public OrganizationVO getOrganizationDetail(String id);

    public ResultMsg checkInAccount(String oid);

    public List<StudentVO> getStudentList();

    public StatisticsVO getStatistics();

    public List<HistoryVO> getHistoryVO();

}
