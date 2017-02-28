package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.Account;
import nju.wjw.entity.History;
import nju.wjw.entity.Student;
import nju.wjw.entity.StudentCard;
import nju.wjw.service.StudentCardService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.StudentCardVO;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * Created by Jerry Wang on 2017/2/20.
 */
@Repository
public class StudentCardImpl implements StudentCardService {

    public ResultMsg studentActive(String account, String password, String money,String studentID) {
        Double moneyd = Double.parseDouble(money);
        String reMesg = "";
        try {
            Account aa = DAOManager.accountDao.getAccountbyAccountNumber(account);
            if(aa.getPassword().equals(password)) {
                if (aa.getBalance() >= moneyd) {
                    aa.setBalance(aa.getBalance() - moneyd);
                    DAOManager.accountDao.update(aa);
                    //记录历史并添加进入学生账户
                    StudentCard studentCard = DAOManager.studentCardDao.get(Integer.parseInt(studentID));
                    studentCard.setLevel(studentCard.getLevel()+moneyd);

                    //记录历史
                    History history = new History();
                    history.setStudent(studentCard.getStudent());
                    history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    history.setAction("进行了会员卡缴费，缴费金额"+money+"元");
                    reMesg+="进行了会员卡缴费，缴费金额"+money+"元成功。";
                    DAOManager.historyDao.save(history);

                    //进行会员激活
                    if(studentCard.getAccountBalance()+moneyd >= 1000 && studentCard.getMemberValidity() == 0){
                        //设置有效日期与有效值
                        studentCard.setMemberValidity(1);
                        Date date = new Date(System.currentTimeMillis());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, +1);
                        studentCard.setValidityTime(new Timestamp(calendar.getTime().getTime()));

                        History history2 = new History();
                        history2.setStudent(studentCard.getStudent());
                        history2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                        history2.setAction("缴费金额"+money+"元后，会员卡被成功激活，有效期至"+calendar.getTime().toString());
                        reMesg += "会员卡已被成功激活，有效期至"+calendar.getTime().toString();
                        DAOManager.historyDao.save(history2);
                    }
                    //添加资金
                    studentCard.setAccountBalance(studentCard.getAccountBalance() + moneyd);
                    DAOManager.studentCardDao.update(studentCard);

                    return new ResultMsg(StateCode.SUCCESS, reMesg);
                } else {
                    return new ResultMsg(StateCode.ACCOUNT_MONEY_NOT_ENOUGH, "账户余额不足！");
                }
            }else{
                return new ResultMsg(StateCode.FAILURE,"密码错误，登录失败");
            }
        }catch (Exception e){
            return new ResultMsg(StateCode.ACCOUNTERROR,"银行账户异常，请检查重新设置。");
        }
    }

    public StudentCardVO studentCardVO(String studentId) {
        Student student = DAOManager.studentDao.get(Integer.parseInt(studentId));

        StudentCardVO re = new StudentCardVO();
        re.memberValidity = student.getStudentCard().getMemberValidity();
        re.time = student.getStudentCard().getValidityTime().toString();
        return re;
    }

    public ResultMsg studentCardInvalid(String studentId) {
        StudentCard st = DAOManager.studentCardDao.get(Integer.parseInt(studentId));
        st.setMemberValidity(0);

        DAOManager.studentCardDao.update(st);
        return new ResultMsg(StateCode.SUCCESS,"学员卡资格已取消。");
    }

    public ResultMsg studentCardValid(String studentId) {
        StudentCard st = DAOManager.studentCardDao.get(Integer.parseInt(studentId));
        st.setMemberValidity(1);

        DAOManager.studentCardDao.update(st);
        return new ResultMsg(StateCode.SUCCESS,"学员卡资格已恢复。");
    }
}
