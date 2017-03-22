package nju.wjw.dao;

import nju.wjw.dao.base.BaseDao;
import nju.wjw.entity.StudentCard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Repository
public class StudentCardDao extends BaseDao<StudentCard,Integer>{

    public List<StudentCard> getAllStudentCard(){
        String hql = "from StudentCard";
        return getListByHQL(hql);
    }

}
