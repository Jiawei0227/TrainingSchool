package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.StudentCardVO;
import nju.wjw.vo.StudentVO;

/**
 * Created by Jerry Wang on 2017/2/20.
 */
public interface StudentCardService {

    /**
     * 激活学生账户缴费
     * @param account
     * @param password
     * @param money
     * @return
     */
    public ResultMsg studentActive(String account,String password,String money,String StudentID);

    /**
     * 获取学生卡信息
     * @param studentId
     * @return
     */
    public StudentCardVO studentCardVO(String studentId);


    /**
     * 取消会员卡资格
     * @param studentId
     * @return
     */
    public ResultMsg studentCardInvalid(String studentId);
    /**
     * 会员卡资格恢复
     * @param studentId
     * @return
     */
    public ResultMsg studentCardValid(String studentId);

    /**
     * 积分兑换账户余额
     * @param points
     * @return
     */
    public ResultMsg points2Money(String points,String stuId);

    /**
     * 修改个人信息
     * @return
     */
    public ResultMsg updatePersonalInfo(StudentVO studentVO);

    /**
     * 定时任务 每天触发一次，检查学员状态
     */
    public void checkStudentCardState();

}
