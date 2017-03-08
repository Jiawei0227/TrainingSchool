package nju.wjw.dao;

import nju.wjw.dao.base.BaseDao;
import nju.wjw.entity.Organization;
import org.springframework.stereotype.Repository;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Repository
public class OrganizationDao extends BaseDao<Organization,Integer> {

    public Organization getByCardNum(String cardNum){
        return getByHQL("from Organization o where o.organizationCard.sid=?",Integer.parseInt(cardNum));
    }


}
