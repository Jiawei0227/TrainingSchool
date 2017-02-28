package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.History;
import nju.wjw.entity.Student;
import nju.wjw.entity.StudentCard;
import nju.wjw.service.StudentService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
                st.studentID = ss.toCardFormat();
                st.balance = ss.getAccountBalance()+"";
                return new ResultMsg(StateCode.SUCCESS, st);
            } else
                return new ResultMsg(StateCode.FAILURE, "您输入的密码有误，请尝试重新输入");
        }catch (Exception e){
            return new ResultMsg(StateCode.LOGINERROR,"账号输入异常，请检查您的学员卡号及密码");
        }
    }

    public ResultMsg studentRegister(StudentVO student){
        Student s = new Student();
        s.setName(student.name);
        s.setAge(Integer.parseInt(student.age));
        s.setEmail(student.email);

        //新建studentCard
        StudentCard studentCard = new StudentCard();
        studentCard.setPassword(student.password);
        studentCard.setAccountBalance(0.0);
        studentCard.setLevel(0.0);
        studentCard.setMemberValidity(0);

        //保存学员卡信息
        DAOManager.studentCardDao.save(studentCard);

        s.setStudentCard(studentCard);
        DAOManager.studentDao.save(s);

        studentCard.setStudent(s);
        //更新studentCard
        DAOManager.studentCardDao.update(studentCard);


        //创建历史记录信息
        History h = new History();
        h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        h.setStudent(s);
        h.setAction("创建了初始账号，学员卡号为"+studentCard.toCardFormat());

        DAOManager.historyDao.save(h);


        return new ResultMsg(StateCode.SUCCESS,"您已成功注册，学员卡号为"+studentCard.toCardFormat()+"");
    }



}
