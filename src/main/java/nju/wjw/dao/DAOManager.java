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

    static{
        ApplicationContext context = ApplicationContextHelper.getApplicationContext();
        accountDao = context.getBean(AccountDao.class);
        studentCardDao = context.getBean(StudentCardDao.class);
        studentDao = context.getBean(StudentDao.class);
        historyDao = context.getBean(HistoryDao.class);
    }

}
