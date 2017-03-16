package nju.wjw.dao;

import nju.wjw.dao.base.BaseDao;
import nju.wjw.entity.History;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Repository
public class HistoryDao extends BaseDao<History,Integer> {

    public List<History> getHistoryBySid(int sid){
        String hql = "from History h where h.student.sid=? order by h.createdAt desc";
        return getListByHQL(hql,sid);
    }

    public List<History> getHistoryByOid(int oid){
        String hql = "from History h where h.organization.oid=? order by h.createdAt desc";
        return getListByHQL(hql,oid);

    }

}
