package nju.wjw.dao;

import nju.wjw.dao.base.BaseDao;
import nju.wjw.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Repository
public class CourseDao extends BaseDao<Course,Integer> {

    public List<Course> getUnCheckedList(){
        String hql = "from Course c where c.isChecked=0";
        return getListByHQL(hql);
    }

    public List<Course> getPassedList(){
        String hql = "from Course c where c.isPassed=1";
        return getListByHQL(hql);
    }

    public String getCourseNumber(){
        return countByHql("select count(*) from Course").toString();
    }

}
