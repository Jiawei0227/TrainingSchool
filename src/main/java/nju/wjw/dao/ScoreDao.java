package nju.wjw.dao;

import nju.wjw.dao.base.BaseDao;
import nju.wjw.entity.Score;
import nju.wjw.util.CourseStudentState;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Repository
public class ScoreDao extends BaseDao<Score,Integer> {

    public Score getScoreByStuIdAndCourseId(int sid,int cid){
        String hql = "from Score s where s.student.sid=? and s.course.cid=?";
        return getByHQL(hql,sid,cid);
    }

    public List<Score> getUncheckedStudentByCourseId(int cid){
        String hql = "from Score s where s.course.cid=? and s.state=?";
        return getListByHQL(hql,cid, CourseStudentState.WAITING);
    }

    public List<Score> getScoreByCourseId(int cid){
        String hql = "from Score s where s.course.cid=? and s.state!=? and s.state!=?";
        return getListByHQL(hql,cid, CourseStudentState.NOTJOINED,CourseStudentState.FAILURED);
    }

    public List<Score> getSuccessScoreByCourseId(int cid){
        String hql = "from Score s where s.course.cid=? and s.state=?";
        return getListByHQL(hql,cid, CourseStudentState.PASSED);
    }

    public List<Score> getSuccessScoreByStudentId(int sid){
        String hql = "from Score s where s.student.sid=? and s.state=?";
        return getListByHQL(hql,sid, CourseStudentState.PASSED);
    }

    public List<Score> getScoreByStudentId(int sid){
        String hql = "from Score s where s.student.sid=? and s.state!=?";
        return getListByHQL(hql,sid,CourseStudentState.NOTJOINED);
    }

}
