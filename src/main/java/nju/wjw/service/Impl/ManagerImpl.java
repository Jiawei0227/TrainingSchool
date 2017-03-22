package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.*;
import nju.wjw.service.ManagerService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    @Override
    public List<OrganizationVO> getOrganizationList() {
        List<Organization> organizations = DAOManager.organizationDao.getAllOrganization();
        return organizations.stream().map(e->{
            OrganizationVO o = new OrganizationVO();
            o.id = e.getOid()+"";
            o.cardNumber = e.getOrganizationCard().toCardFormat();
            o.name = e.getName();
            o.courseNumber = e.getCourseList().size()+"";
            o.email = e.getEmail();
            o.money = e.getMoney()+"";
            return o;
        }).collect(Collectors.toList());
    }

    @Override
    public OrganizationVO getOrganizationDetail(String id) {
        Organization organization = DAOManager.organizationDao.get(Integer.parseInt(id));
        OrganizationVO re = new OrganizationVO();
        re.email = organization.getEmail();
        re.id = organization.getOid()+"";
        re.money = organization.getMoney()+"";
        re.description = organization.getDescription();
        re.name = organization.getName();
        List<Course> courses = organization.getCourseList();
        re.courseList = courses.stream().map(e->{
            CourseVO c = new CourseVO();
            c.isChecked = e.getIsChecked();
            c.isPassed = e.getIsPassed();
            c.name = e.getName();
            c.id = e.getCid()+"";
            c.price = e.getPrice()+"";
            c.startTime = e.getStartTime().toString();
            c.endTime = e.getEndTime().toString();
            return c;
        }).collect(Collectors.toList());

        return re;
    }

    @Override
    public ResultMsg checkInAccount(String oid) {
        try {
            Organization organization = DAOManager.organizationDao.get(Integer.parseInt(oid));
            double money = organization.getMoney();
            Manager manager = DAOManager.managerDao.getManager();
            Account oAccount = organization.getAccount();
            Account mAccount = manager.getAccount();

            oAccount.setBalance(oAccount.getBalance() + money);
            DAOManager.accountDao.update(oAccount);

            mAccount.setBalance(mAccount.getBalance() - money);
            DAOManager.accountDao.update(mAccount);
            System.out.println(mAccount.getBalance()+"sssssdasdsadssssssss");

            organization.setMoney(0.0);
            DAOManager.organizationDao.update(organization);

            History history = new History();
            history.setOrganization(organization);
            history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            history.setAction("经理进行了账户转账结算，账户"+oAccount.getAccountNumber()+"获得金额"+money);
            DAOManager.historyDao.save(history);

            History history2 = new History();
            history2.setManager(DAOManager.managerDao.getManager());
            history2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            history2.setAction("经理进行了账户转账结算，给"+organization.getName()+"机构转账"+money+"元，目前账户余额"+mAccount.getBalance());
            DAOManager.historyDao.save(history2);

        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.FAILURE,"未知的错误");
        }

        return new ResultMsg(StateCode.SUCCESS,"转账成功，该机构成功获得酬劳");
    }

    @Override
    public List<StudentVO> getStudentList() {
        List<Student> s= DAOManager.studentDao.getAllStudent();
        return s.stream().map(e->{
            StudentVO studentVO = new StudentVO();
            studentVO.studentID = e.getSid()+"";
            StudentCard ss = e.getStudentCard();
            if(ss==null){
                studentVO.balance = null;
                studentVO.studentCardID = null;
                studentVO.level = null;
            }else {
                studentVO.balance = ss.getAccountBalance()+"";
                studentVO.studentCardID = ss.toCardFormat();
                studentVO.level = e.getStudentCard().getRank();
            }
            studentVO.age = e.getAge()+"";
            studentVO.email = e.getEmail();
            studentVO.name = e.getName();
            return studentVO;

        }).collect(Collectors.toList());
    }

    @Override
    public StatisticsVO getStatistics() {
        StatisticsVO statisticsVO = new StatisticsVO();
        statisticsVO.account_Balance = DAOManager.managerDao.getManager().getAccount().getBalance()+"";
        statisticsVO.course_Number = DAOManager.courseDao.getCourseNumber();
        statisticsVO.organ_Number = DAOManager.organizationDao.getOrganizationCount();
        statisticsVO.star_stu_Number = DAOManager.studentDao.getStarStudentCount();
        statisticsVO.month_stu_Number = DAOManager.studentDao.getMonthStudentCount();
        statisticsVO.sun_stu_Number = DAOManager.studentDao.getSunStudentCount();
        statisticsVO.diamond_stu_Number = DAOManager.studentDao.getDiamondStudentCount();
        return statisticsVO;
    }

    @Override
    public List<HistoryVO> getHistoryVO() {
        List<History> histories = DAOManager.historyDao.getHistoryByMid(DAOManager.managerDao.getManager().getId());
        if(histories==null || histories.isEmpty())
            return null;

        return histories.stream().map(e->{
            HistoryVO historyVO = new HistoryVO();
            historyVO.createdAt = e.getCreatedAt().toString();
            historyVO.action = e.getAction();
            return historyVO;
        }).collect(Collectors.toList());
    }
}
