package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.*;
import nju.wjw.service.StudentService;
import nju.wjw.util.CourseStudentState;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jerry Wang on 2017/2/12.
 */
@Service
public class StudentServiceImpl implements StudentService {

    public ResultMsg studentLogin(String cardNo,String password){

        int cardId = Integer.parseInt(cardNo);
        try {
            StudentCard ss = DAOManager.studentCardDao.get(cardId);
            if (ss.getPassword().equals(password)) {

                StudentVO st = new StudentVO();
                st.name = ss.getStudent().getName();
                st.age = ss.getStudent().getAge()+"";
                st.studentID = ss.getStudent().getSid()+"";
                st.studentCardID = ss.toCardFormat();
                st.balance = ss.getAccountBalance()+"";
                return new ResultMsg(StateCode.SUCCESS, st);
            } else
                return new ResultMsg(StateCode.FAILURE, "您输入的密码有误，请尝试重新输入");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.LOGINERROR,"账号输入异常，请检查您的学员卡号及密码");
        }
    }

    @Override
    public List<CourseVO> getAllCourses() {
        return DAOManager.courseDao.getPassedList().stream().map(e->{
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
    public CourseDetailViewVO getCoursesDetail(String id, String studentId) {
        CourseDetailViewVO re = new CourseDetailViewVO();

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

        Date date = new Date(System.currentTimeMillis());

        if(c.getEndTime().after(date)){
            re.isPastDue = false;
        }else
            re.isPastDue = true;

        Score score = DAOManager.scoreDao.getScoreByStuIdAndCourseId(Integer.parseInt(studentId),c.getCid());

        if(score==null)
            re.state = CourseStudentState.NOTJOINED;
        else
            re.state = score.getState();

        Organization organization = c.getOrganization();
        OrganizationVO o = new OrganizationVO();
        o.id = organization.getOid()+"";
        o.name = organization.getName();
        o.description = organization.getDescription();
        o.email = organization.getEmail();


        re.courseVO = courseVO;
        re.organizationVO = o;

        return re;
    }

    @Override
    public ResultMsg addCourse(String cid, String sid) {
        Student student = DAOManager.studentDao.get(Integer.parseInt(sid));
        Course course = DAOManager.courseDao.get(Integer.parseInt(cid));
        Score score = DAOManager.scoreDao.getScoreByStuIdAndCourseId(Integer.parseInt(sid),Integer.parseInt(cid));
        if(score!=null && score.getState().equals(CourseStudentState.PASSED)) {
            try {
                score.setState(CourseStudentState.NOTJOINED);
                DAOManager.scoreDao.update(score);
            }catch (Exception e){
                e.printStackTrace();
                return new ResultMsg(StateCode.FAILURE,"退课失败，异常发生！");
            }

            History history = new History();
            history.setStudent(student);
            history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            history.setAction("进行了退课操作，退出了课程"+course.getName()+",课程编号："+course.getCid());
            DAOManager.historyDao.save(history);

            return new ResultMsg(StateCode.SUCCESS,"退课成功！");
        }else if(score!=null && score.getState().equals(CourseStudentState.NOTJOINED)){
            try {
                score.setState(CourseStudentState.PASSED);
                DAOManager.scoreDao.update(score);
            }catch (Exception e){
                e.printStackTrace();
                return new ResultMsg(StateCode.FAILURE,"选课失败，异常发生！");
            }

            History history = new History();
            history.setStudent(student);
            history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            history.setAction("进行了重新选课操作，选择了课程"+course.getName()+",课程编号："+course.getCid()+".由于已经退过该课程，并未造成退款。");
            DAOManager.historyDao.save(history);

            return new ResultMsg(StateCode.SUCCESS,"选课成功！");
        }else if(score!=null && score.getState().equals(CourseStudentState.FAILURED)){

            try {
                score.setState(CourseStudentState.WAITING);
                DAOManager.scoreDao.update(score);
            }catch (Exception e){
                e.printStackTrace();
                return new ResultMsg(StateCode.FAILURE,"选课失败，异常发生！");
            }

            History history = new History();
            history.setStudent(student);
            history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            history.setAction("重新进行了选课操作，选择了课程"+course.getName()+",课程编号："+course.getCid()+".");
            DAOManager.historyDao.save(history);

            return new ResultMsg(StateCode.SUCCESS,"选课成功！");
        } else if(score == null){
            score = new Score();
            score.setState(CourseStudentState.WAITING);
            score.setStudent(student);
            score.setCourse(course);
            DAOManager.scoreDao.save(score);

            History history = new History();
            history.setStudent(student);
            history.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            history.setAction("进行了选课操作，选择了课程"+course.getName()+",课程编号："+course.getCid()+".等待审核");
            DAOManager.historyDao.save(history);

            return new ResultMsg(StateCode.SUCCESS,"选课成功！请确保账户资金充足，否则无法通过审核。");
        }


        return new ResultMsg(StateCode.FAILURE,"您的申请正在审核中...");
    }

    @Override
    public List<StudentCourseVO> getStudentCourse(String sid) {
        List<Score> scores = DAOManager.scoreDao.getScoreByStudentId(Integer.parseInt(sid));
        return scores.stream().map(e->{
            StudentCourseVO s = new StudentCourseVO();
            Course c = e.getCourse();
            s.id = c.getCid()+"";
            s.name = c.getName();
            s.description = c.getDescription();
            s.startTime = c.getStartTime().toString();
            s.endTime = c.getEndTime().toString();
            s.price = c.getPrice()+"";
            s.teacher = c.getTeacher();
            s.state = e.getState();

            return s;

        }).collect(Collectors.toList());
    }

    @Override
    public List<HistoryVO> getHistory(String sid) {
        List<History> histories = DAOManager.historyDao.getHistoryBySid(Integer.parseInt(sid));
        if(histories==null || histories.isEmpty())
            return null;

        return histories.stream().map(e->{
            HistoryVO historyVO = new HistoryVO();
            historyVO.createdAt = e.getCreatedAt().toString();
            historyVO.action = e.getAction();
            return historyVO;
        }).collect(Collectors.toList());
    }


    public ResultMsg studentRegister(StudentVO student){
        Student s = new Student();
        s.setName(student.name);
        s.setAge(Integer.parseInt(student.age));
        s.setEmail(student.email);
        DAOManager.studentDao.save(s);

        //新建studentCard
        StudentCard studentCard = new StudentCard();
        studentCard.setPassword(student.password);
        studentCard.setAccountBalance(0.0);
        studentCard.setLevel(0.0);
        studentCard.setMemberValidity(0);
        studentCard.setStudent(s);
        //保存学员卡信息
        DAOManager.studentCardDao.save(studentCard);

        s.setStudentCard(studentCard);
        DAOManager.studentDao.update(s);


        //创建历史记录信息
        History h = new History();
        h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        h.setStudent(s);
        h.setAction("创建了初始账号，学员卡号为"+studentCard.toCardFormat());

        DAOManager.historyDao.save(h);


        return new ResultMsg(StateCode.SUCCESS,"您已成功注册，学员卡号为"+studentCard.toCardFormat()+"");
    }



}
