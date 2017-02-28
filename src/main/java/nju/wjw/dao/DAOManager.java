package nju.wjw.dao;

import nju.wjw.util.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;

/**
 * Created by Jerry Wang on 2017/2/13.
 */
public class DAOManager {

    public final static AccountDao accountDao;

    public final static StudentCardDao studentCardDao;

    public final static StudentDao studentDao;

    public final static HistoryDao historyDao;

    public final static CourseDao courseDao;

    public final static ManagerDao managerDao;

    public final static OrganizationDao organizationDao;

    public final static ScoreDao scoreDao;

    static{
        ApplicationContext context = ApplicationContextHelper.getApplicationContext();
        accountDao = context.getBean(AccountDao.class);
        studentCardDao = context.getBean(StudentCardDao.class);
        studentDao = context.getBean(StudentDao.class);
        historyDao = context.getBean(HistoryDao.class);
        courseDao = context.getBean(CourseDao.class);
        managerDao = context.getBean(ManagerDao.class);
        organizationDao = context.getBean(OrganizationDao.class);
        scoreDao = context.getBean(ScoreDao.class);
    }

}
