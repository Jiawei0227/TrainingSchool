package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.*;
import nju.wjw.service.OrganizationService;
import nju.wjw.util.CourseStudentState;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jerry Wang on 2017/3/6.
 */
@Repository
public class OrganizationImpl implements OrganizationService {
    public ResultMsg organizationRegister(OrganizationVO organizationVO) {
        Organization s = new Organization();
        s.setName(organizationVO.name);
        s.setEmail(organizationVO.email);
        s.setMoney(0.0);
        s.setDescription(organizationVO.description);

        Account account = DAOManager.accountDao.getAccountbyAccountNumber(organizationVO.account_id);

        if(account == null)
            return new ResultMsg(StateCode.ACCOUNTERROR,"银行卡号不存在");

        s.setAccount(account);

        DAOManager.organizationDao.save(s);

        //新建organizationCard
        OrganizationCard organizationCard = new OrganizationCard();
        organizationCard.setPassword(organizationVO.password);
        organizationCard.setOrganization(s);
        //保存学员卡信息
        DAOManager.organizationCardDao.save(organizationCard);

        s.setOrganizationCard(organizationCard);
        DAOManager.organizationDao.update(s);


        //创建历史记录信息
        History h = new History();
        h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        h.setOrganization(s);
        h.setAction("创建了初始账号，机构卡号为"+organizationCard.toCardFormat());

        DAOManager.historyDao.save(h);


        return new ResultMsg(StateCode.SUCCESS,"您已成功注册，机构号为"+organizationCard.toCardFormat()+"");
    }

    public ResultMsg organizationLogin(String cardNo, String password) {

        int cardId = Integer.parseInt(cardNo);
        try {
            OrganizationCard ss = DAOManager.organizationCardDao.get(cardId);
            Organization organization = DAOManager.organizationDao.getByCardNum(cardNo);
            if (ss.getPassword().equals(password)) {

                OrganizationVO st = new OrganizationVO();
                st.name = ss.getOrganization().getName();
                st.account_id = organization.getAccount().getAccountNumber();
                st.email = organization.getEmail();
                st.password = ss.getPassword();
                st.id = organization.getOid()+"";
                st.cardNumber = ss.toCardFormat();

                return new ResultMsg(StateCode.SUCCESS, st);
            } else
                return new ResultMsg(StateCode.FAILURE, "您输入的密码有误，请尝试重新输入");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.LOGINERROR,"账号输入异常，请检查您的卡号及密码");
        }
    }


    public ResultMsg newCourseApply(CourseVO courseVO,String oid) {
        try {
            Organization organization = DAOManager.organizationDao.getByCardNum(oid);

            Course course = new Course();
            course.setName(courseVO.name);
            course.setDescription(courseVO.description);
            course.setTeacher(courseVO.teacher);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date(sdf.parse(courseVO.startTime).getTime());
            course.setStartTime(startDate);
            Date endDate = new Date(sdf.parse(courseVO.endTime).getTime());
            course.setEndTime(endDate);
            course.setPrice(Double.parseDouble(courseVO.price));
            course.setIsChecked(0);
            course.setOrganization(organization);

            DAOManager.courseDao.save(course);

            History h = new History();
            h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            h.setOrganization(organization);
            h.setAction(String.format("申请了创建新的课程，课程名：%s；课程描述信息：%s；代课老师：%s，课程价格：%s人民币，开课时间：%s，闭课时间：%s",
                    courseVO.name,courseVO.description,courseVO.teacher,courseVO.price,courseVO.startTime,courseVO.endTime));
            DAOManager.historyDao.save(h);

        }catch (ParseException e){
            e.printStackTrace();
            return new ResultMsg(StateCode.TIME_ERROR,"时间格式有误，请检查上课时间格式");
        }

        return new ResultMsg(StateCode.SUCCESS,"申请成功，请等待经理批准");
    }

    @Override
    public List<OrganCourseVO> getCourseList(String organizationId) {
        Organization organization = DAOManager.organizationDao.get(Integer.parseInt(organizationId));

        return organization.getCourseList().stream().map(e->{
            OrganCourseVO organCourseVO = new OrganCourseVO();
            organCourseVO.courseId = e.getCid()+"";
            organCourseVO.courseName = e.getName();
            organCourseVO.startTime = e.getStartTime().toString();
            organCourseVO.endTime = e.getEndTime().toString();
            if(e.getIsChecked()==0){
                organCourseVO.state = "课程正在审核中";
            }else{
                if(e.getIsPassed()==1)
                    organCourseVO.state = "审核通过";
                else
                    organCourseVO.state = "审核未通过";
            }
            organCourseVO.waitingNumber = DAOManager.scoreDao.getUncheckedStudentByCourseId(e.getCid()).size()+"";
            return organCourseVO;
        }).collect(Collectors.toList());


    }

    @Override
    public CourseConfirmDetailViewVO getCourseConfirm(String cid) {
        CourseConfirmDetailViewVO re = new CourseConfirmDetailViewVO();

        Course c = DAOManager.courseDao.get(Integer.parseInt(cid));

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

        Organization organization = c.getOrganization();
        OrganizationVO o = new OrganizationVO();
        o.id = organization.getOid()+"";
        o.name = organization.getName();
        o.description = organization.getDescription();
        o.email = organization.getEmail();

        re.studentScoreVOs = DAOManager.scoreDao.getScoreByCourseId(Integer.parseInt(cid)).stream().map(e->{
            StudentScoreVO studentScoreVO = new StudentScoreVO();
            studentScoreVO.sid = e.getStudent().getSid().toString();
            studentScoreVO.score = e.getScore()+"";
            studentScoreVO.state = e.getState();
            if(e.getStudent().getStudentCard()!=null)
                studentScoreVO.studentCard = e.getStudent().getStudentCard().toCardFormat();
            studentScoreVO.name = e.getStudent().getName();
            return studentScoreVO;
        }).collect(Collectors.toList());


        re.courseVO = courseVO;
        re.organizationVO = o;

        return re;
    }

    @Override
    public ResultMsg CourseConfirmPost(String sid, String cid, String passCode) {
        History history = new History();
        history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        History history2 = new History();
        history2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            Score score = DAOManager.scoreDao.getScoreByStuIdAndCourseId(Integer.parseInt(sid), Integer.parseInt(cid));
            Course course = score.getCourse();
            Student student = score.getStudent();
            double price = course.getPrice();

            if(passCode.equals("0")){
                score.setState(CourseStudentState.FAILURED);

                DAOManager.scoreDao.update(score);

                history.setOrganization(course.getOrganization());
                history.setAction("拒绝了学生"+student.getName()+"参加"+course.getName()+"的申请未通过审核。");
                DAOManager.historyDao.save(history);


                history2.setOrganization(course.getOrganization());
                history2.setAction(student.getName()+"参加"+course.getName()+"的申请被拒绝，未通过审核。");
                DAOManager.historyDao.save(history2);
                return new ResultMsg(StateCode.SUCCESS,"拒绝通过.");
            }

            if(student.getStudentCard().getAccountBalance()<price){
                score.setState(CourseStudentState.FAILURED);
                DAOManager.scoreDao.update(score);

                history.setOrganization(course.getOrganization());
                history.setAction("通过了学生"+student.getName()+"参加"+course.getName()+"的申请，学生账户余额不足，未通过审核。");
                DAOManager.historyDao.save(history);


                history2.setOrganization(course.getOrganization());
                history2.setAction("通过了"+student.getName()+"参加"+course.getName()+"的申请，由于账户余额不足，未通过审核。");
                DAOManager.historyDao.save(history2);
                return new ResultMsg(StateCode.FAILURE,"该学生账户余额不足，审核未通过.");
            }

            StudentCard studentCard = student.getStudentCard();
            studentCard.setAccountBalance(studentCard.getAccountBalance()-course.getPrice());
            DAOManager.studentCardDao.update(studentCard);

            history.setOrganization(course.getOrganization());
            history.setAction("通过了学生"+student.getName()+"参加"+course.getName()+"的申请,扣款成功，机构获得资金"+course.getPrice()+"元。");
            DAOManager.historyDao.save(history);

            Organization organization = course.getOrganization();
            organization.setMoney(organization.getMoney()+course.getPrice());
            DAOManager.organizationDao.update(organization);
            history2.setStudent(student);
            history2.setAction("通过了"+student.getName()+"参加"+course.getName()+"的申请扣款成功，扣除资金"+course.getPrice()+"元。");
            DAOManager.historyDao.save(history2);

            score.setState(CourseStudentState.PASSED);
            DAOManager.scoreDao.update(score);



        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.FAILURE,"操作失败,未知的错误发生");
        }
        return new ResultMsg(StateCode.SUCCESS,"成功");
    }

    @Override
    public ResultMsg courseAddStudent(StudentVO studentVO,String cid) {
        Course course = DAOManager.courseDao.get(Integer.parseInt(cid));
        History history = new History();
        history.setOrganization(course.getOrganization());
        history.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        //不存在学员卡 线下交易金钱
        if(studentVO.studentCardID==null||studentVO.studentCardID.equals("")){
            Student student = new Student();
            student.setEmail(studentVO.email);
            student.setAge(Integer.parseInt(studentVO.age));
            student.setName(studentVO.name);
            DAOManager.studentDao.save(student);

            Score score = new Score();
            score.setState(CourseStudentState.PASSED);
            score.setCourse(course);
            score.setStudent(student);
            DAOManager.scoreDao.save(score);

            history.setAction("添加了新的线下学员"+studentVO.name+"，交易发生在线下");
            DAOManager.historyDao.save(history);

            //存在学员卡 需要进行线上交易
        }else{
            StudentCard studentCard = DAOManager.studentCardDao.get(Integer.parseInt(studentVO.studentCardID));
            if(studentCard == null){
                return new ResultMsg(StateCode.FAILURE,"添加失败，不存在该学员卡");
            }
            Student student = studentCard.getStudent();
            Score score = DAOManager.scoreDao.getScoreByStuIdAndCourseId(student.getSid(),course.getCid());
            //检查是否已经添加过该学员
            if(score!= null&&score.getState().equals(CourseStudentState.PASSED)){
                return new ResultMsg(StateCode.FAILURE,"添加失败，该学员已经在该班级中");
            }else{
                //判断是否钱足够
                if(student.getStudentCard().getAccountBalance()>=course.getPrice()){

                    studentCard.setAccountBalance(studentCard.getAccountBalance()-course.getPrice());
                    DAOManager.studentCardDao.update(studentCard);

                    history.setOrganization(course.getOrganization());
                    history.setAction("通过了学生"+student.getName()+"参加"+course.getName()+"的申请,扣款成功，机构获得资金"+course.getPrice()+"元。");
                    DAOManager.historyDao.save(history);

                    Organization organization = course.getOrganization();
                    organization.setMoney(organization.getMoney()+course.getPrice());
                    DAOManager.organizationDao.update(organization);
                    History history2 = new History();
                    history2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    history2.setStudent(student);
                    history2.setAction("成功加入课程"+course.getName()+"，扣款成功，扣除资金"+course.getPrice()+"元。");
                    DAOManager.historyDao.save(history2);

                    if(score!=null) {
                        score.setState(CourseStudentState.PASSED);
                        DAOManager.scoreDao.update(score);
                    }else{
                        score = new Score();
                        score.setCourse(course);
                        score.setStudent(student);
                        score.setState(CourseStudentState.PASSED);
                        DAOManager.scoreDao.save(score);
                    }

                }
            }
        }
        return new ResultMsg(StateCode.SUCCESS,"操作成功，添加学员到课程中。");
    }

    @Override
    public ResultMsg updateCourseApply(CourseVO courseVO) {
        try {

            Course course = DAOManager.courseDao.get(Integer.parseInt(courseVO.id));
            course.setName(courseVO.name);
            course.setDescription(courseVO.description);
            course.setTeacher(courseVO.teacher);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date(sdf.parse(courseVO.startTime).getTime());
            course.setStartTime(startDate);
            Date endDate = new Date(sdf.parse(courseVO.endTime).getTime());
            course.setEndTime(endDate);
            course.setPrice(Double.parseDouble(courseVO.price));
            course.setIsChecked(0);
            course.setIsPassed(0);

            DAOManager.courseDao.update(course);

            History h = new History();
            h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            h.setOrganization(course.getOrganization());
            h.setAction(String.format("申请修改课程，课程名：%s；课程描述信息：%s；代课老师：%s，课程价格：%s人民币，开课时间：%s，闭课时间：%s",
                    courseVO.name,courseVO.description,courseVO.teacher,courseVO.price,courseVO.startTime,courseVO.endTime));
            DAOManager.historyDao.save(h);

        }catch (ParseException e){
            e.printStackTrace();
            return new ResultMsg(StateCode.TIME_ERROR,"时间格式有误，请检查上课时间格式");
        }

        return new ResultMsg(StateCode.SUCCESS,"申请成功，请等待经理批准");
    }

    @Override
    public CourseVO getCourseVO(String id) {
        Course course = DAOManager.courseDao.get(Integer.parseInt(id));
        CourseVO courseVO = new CourseVO();
        courseVO.id = course.getCid()+"";
        courseVO.name = course.getName();
        courseVO.startTime = course.getStartTime().toString();
        courseVO.endTime = course.getEndTime().toString();
        courseVO.teacher = course.getTeacher();
        courseVO.price = course.getPrice()+"";
        courseVO.description = course.getDescription();
        return courseVO;

    }

    @Override
    public List<HistoryVO> getHistroy(String oid) {
        List<History> histories = DAOManager.historyDao.getHistoryByOid(Integer.parseInt(oid));
        if(histories==null||histories.isEmpty())
            return null;
        return histories.stream().map(e->{
            HistoryVO historyVO = new HistoryVO();
            historyVO.createdAt = e.getCreatedAt().toString();
            historyVO.action = e.getAction();
            return historyVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StudentScoreVO> getScore(String cid) {
        List<Score> scores = DAOManager.scoreDao.getSuccessScoreByCourseId(Integer.parseInt(cid));
        return scores.stream().map(e->{
            StudentScoreVO studentScoreVO = new StudentScoreVO();
            studentScoreVO.name = e.getStudent().getName();
            studentScoreVO.sid = e.getStudent().getSid()+"";
            studentScoreVO.score = e.getScore()+"";
            studentScoreVO.back = e.getBack();
            return studentScoreVO;

        }).collect(Collectors.toList());
    }

    @Override
    public ResultMsg setScores() {
        return null;
    }
}
