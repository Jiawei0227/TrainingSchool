package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.CourseVO;
import nju.wjw.vo.OrganizationVO;

/**
 * Created by Jerry Wang on 2017/3/6.
 */
public interface OrganizationService {

    /**
     * 企业机构注册
     * @param
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
}
