package nju.wjw.dao;

import nju.wjw.dao.base.BaseDao;
import nju.wjw.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Repository
public class StudentDao extends BaseDao<Student,Integer> {

    public List<Student> getAllStudent(){
        return getListByHQL("from Student");
    }

    public String getStarStudentCount(){
        return countByHql("select count(*) from Student s where s.studentCard.level<5000").toString();
    }

    public String getMonthStudentCount(){
        return countByHql("select count(*) from Student s where s.studentCard.level>=5000 and s.studentCard.level<10000").toString();
    }

    public String getSunStudentCount(){
        return countByHql("select count(*) from Student s where s.studentCard.level>=10000 and s.studentCard.level<20000").toString();
    }

    public String getDiamondStudentCount(){
        return countByHql("select count(*) from Student s where s.studentCard.level>=20000").toString();
    }

}
