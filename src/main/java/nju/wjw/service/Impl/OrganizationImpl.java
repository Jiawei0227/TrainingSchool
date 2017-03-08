package nju.wjw.service.Impl;

import nju.wjw.dao.DAOManager;
import nju.wjw.entity.*;
import nju.wjw.service.OrganizationService;
import nju.wjw.util.ResultMsg;
import nju.wjw.util.StateCode;
import nju.wjw.vo.CourseVO;
import nju.wjw.vo.OrganizationVO;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Jerry Wang on 2017/3/6.
 */
@Repository
public class OrganizationImpl implements OrganizationService {
    public ResultMsg organizationRegister(OrganizationVO organizationVO) {
        Organization s = new Organization();
        s.setName(organizationVO.name);
        s.setEmail(organizationVO.email);

        Account account = DAOManager.accountDao.getAccountbyAccountNumber(organizationVO.account_id);

        if(account == null)
            return new ResultMsg(StateCode.ACCOUNTERROR,"银行卡号不存在");

        s.setAccount(account);

        DAOManager.organizationDao.save(s);

        //新建organizationCard
        OrganizationCard organizationCard = new OrganizationCard();
        organizationCard.setPassword(organizationVO.password);
        organizationCard.setOrganization(s);
        //保存学员卡信息
        DAOManager.organizationCardDao.save(organizationCard);

        s.setOrganizationCard(organizationCard);
        DAOManager.organizationDao.update(s);


        //创建历史记录信息
        History h = new History();
        h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        h.setOrganization(s);
        h.setAction("创建了初始账号，机构卡号为"+organizationCard.toCardFormat());

        DAOManager.historyDao.save(h);


        return new ResultMsg(StateCode.SUCCESS,"您已成功注册，机构号为"+organizationCard.toCardFormat()+"");
    }

    public ResultMsg organizationLogin(String cardNo, String password) {

        int cardId = Integer.parseInt(cardNo);
        try {
            OrganizationCard ss = DAOManager.organizationCardDao.get(cardId);
            Organization organization = DAOManager.organizationDao.getByCardNum(cardNo);
            if (ss.getPassword().equals(password)) {

                OrganizationVO st = new OrganizationVO();
                st.name = ss.getOrganization().getName();
                st.account_id = organization.getAccount().getAccountNumber();
                st.email = organization.getEmail();
                st.password = ss.getPassword();
                st.id = organization.getOid()+"";
                st.cardNumber = ss.toCardFormat();

                return new ResultMsg(StateCode.SUCCESS, st);
            } else
                return new ResultMsg(StateCode.FAILURE, "您输入的密码有误，请尝试重新输入");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg(StateCode.LOGINERROR,"账号输入异常，请检查您的卡号及密码");
        }
    }

    public ResultMsg newCourseApply(CourseVO courseVO,String oid) {
        try {
            Organization organization = DAOManager.organizationDao.getByCardNum(oid);

            Course course = new Course();
            course.setName(courseVO.name);
            course.setDescription(courseVO.description);
            course.setTeacher(courseVO.teacher);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date startDate = new Date(sdf.parse(courseVO.startTime).getTime());
            course.setStartTime(startDate);
            Date endDate = new Date(sdf.parse(courseVO.endTime).getTime());
            course.setEndTime(endDate);
            course.setPrice(Double.parseDouble(courseVO.price));
            course.setIsChecked(0);
            course.setOrganization(organization);

            DAOManager.courseDao.save(course);

            History h = new History();
            h.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            h.setOrganization(organization);
            h.setAction(String.format("申请了创建新的课程，课程名：%s；课程描述信息：%s；代课老师：%s，课程价格：%s人民币，开课时间：%s，闭课时间：%s",
                    courseVO.name,courseVO.description,courseVO.teacher,courseVO.price,courseVO.startTime,courseVO.endTime));
            DAOManager.historyDao.save(h);

        }catch (ParseException e){
            e.printStackTrace();
            return new ResultMsg(StateCode.TIME_ERROR,"时间格式有误，请检查上课时间格式");
        }

        return new ResultMsg(StateCode.SUCCESS,"申请成功，请等待经理批准");
    }
}
