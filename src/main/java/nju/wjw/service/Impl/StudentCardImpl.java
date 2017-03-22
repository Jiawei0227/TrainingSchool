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
import nju.wjw.vo.StudentVO;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


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
                    StudentCard studentCard = DAOManager.studentDao.get(Integer.parseInt(studentID)).getStudentCard();

                    studentCard.setLevel(studentCard.getLevel()+moneyd);
                    studentCard.setPoints(studentCard.getPoints()+moneyd);
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

                    Account account1 =DAOManager.managerDao.getManager().getAccount();
                    account1.setBalance(account1.getBalance()+moneyd);
                    DAOManager.accountDao.update(account1);

                    History history3 = new History();
                    history3.setManager(DAOManager.managerDao.getManager());
                    history3.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    history3.setAction("学员卡为"+studentCard.toCardFormat()+"的学生缴费金额"+money+"元。现在账户余额为"+DAOManager.managerDao.getManager().getAccount().getBalance()+"元");
                    DAOManager.historyDao.save(history3);


                    return new ResultMsg(StateCode.SUCCESS, reMesg);
                } else {
                    return new ResultMsg(StateCode.ACCOUNT_MONEY_NOT_ENOUGH, "账户余额不足！");
                }
            }else{
                return new ResultMsg(StateCode.FAILURE,"密码错误，登录失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.ACCOUNTERROR,"银行账户异常，请检查重新设置。");
        }
    }

    public StudentCardVO studentCardVO(String studentId) {

        StudentCard s = DAOManager.studentDao.get(Integer.parseInt(studentId)).getStudentCard();

        StudentCardVO re = new StudentCardVO();
        re.balance = s.getAccountBalance()+"";
        re.points = s.getPoints()+"";
        switch (s.getRank()){
            case STAR: re.rank="星级用户"; break;
            case MONTH: re.rank="月亮级用户"; break;
            case SUN: re.rank = "太阳级用户"; break;
            case DIAMOND: re.rank="钻石级用户"; break;
        }
        re.level = s.getAnotherLevel();
        re.memberValidity = s.getMemberValidity();
        re.time = s.getValidityTime().toString();
        return re;
    }

    public ResultMsg studentCardInvalid(String studentCardID) {
        StudentCard st = DAOManager.studentCardDao.get(Integer.parseInt(studentCardID));
        st.setMemberValidity(0);

        DAOManager.studentCardDao.update(st);
        return new ResultMsg(StateCode.SUCCESS,"学员卡资格已取消。");
    }

    public ResultMsg studentCardValid(String studentId) {
        StudentCard st = DAOManager.studentCardDao.get(Integer.parseInt(studentId));
        if(st.getAccountBalance()>=1000){
            st.setMemberValidity(1);

            DAOManager.studentCardDao.update(st);
        }else{
            return new ResultMsg(StateCode.SUCCESS,"学员卡余额不足 不能恢复。");
        }

        return new ResultMsg(StateCode.SUCCESS,"学员卡资格已恢复。");
    }

    @Override
    public ResultMsg points2Money(String points,String stuId) {
        Double point;
        try{
            point = Double.parseDouble(points);
            StudentCard studentCard = DAOManager.studentDao.get(Integer.parseInt(stuId)).getStudentCard();
            //积分足够
            if(studentCard.getPoints()>=point) {
                studentCard.setPoints(studentCard.getPoints() - point);
                studentCard.setAccountBalance(studentCard.getAccountBalance()+point*0.3);
                DAOManager.studentCardDao.update(studentCard);

                History history = new History();
                history.setStudent(studentCard.getStudent());
                history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                history.setAction("将"+points+"积分成功转换为"+point*0.3+"元余额存入账户中。");
                DAOManager.historyDao.save(history);
            }else
                return new ResultMsg(StateCode.FAILURE,"积分数不足");



        }catch (NumberFormatException e){
            return new ResultMsg(StateCode.FAILURE,"积分数字格式不正确");
        }
        return new ResultMsg(StateCode.SUCCESS,"积分转换成功，成功转换为"+point*0.3+"元余额存入账户中");
    }

    @Override
    public ResultMsg updatePersonalInfo(StudentVO studentVO) {
        try {
            StudentCard studentCard = DAOManager.studentCardDao.get(Integer.parseInt(studentVO.studentCardID));
            studentCard.setPassword(studentVO.password);
            DAOManager.studentCardDao.update(studentCard);
            Student student = studentCard.getStudent();
            student.setName(studentVO.name);
            student.setAge(Integer.parseInt(studentVO.age));
            student.setEmail(studentVO.email);

            DAOManager.studentDao.update(student);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return new ResultMsg(StateCode.FAILURE,"年龄格式不正确");
        }

        ResultMsg re = new ResultMsg(StateCode.SUCCESS,"修改成功");
        return  re;
    }

    @Override
    public void checkStudentCardState() {
        List<StudentCard> studentCardList = DAOManager.studentCardDao.getAllStudentCard();
        studentCardList.stream().forEach(e -> {
            //过期时间在当前时间之前
            if(e.getValidityTime().before(new Timestamp(System.currentTimeMillis()))) {
                //卡上余额够1000
                if(e.getAccountBalance()>=1000){
                    Date date = new Date(System.currentTimeMillis());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.YEAR, +1);
                    e.setValidityTime(new Timestamp(calendar.getTime().getTime()));
                    e.setMemberValidity(1);

                    History history = new History();
                    history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    history.setStudent(e.getStudent());
                    history.setAction("会员卡已过有效期，卡上余额大于1000，恢复会员记录。学员卡有效期延长至"+date.toString());
                    DAOManager.historyDao.save(history);
                }else {
                    e.setMemberValidity(0);
                    History history = new History();
                    history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    history.setStudent(e.getStudent());
                    history.setAction("会员卡已过有效期，卡上余额不足，暂停会员记录。重新充值后方可激活会员卡");
                    DAOManager.historyDao.save(history);
                }
                DAOManager.studentCardDao.update(e);

            }
        });
    }


}
