package nju.wjw.service;

import nju.wjw.util.ResultMsg;
import nju.wjw.vo.StudentVO;

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


}
