package nju.wjw.entity;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Entity
public class StudentCard {

    /**
     * 身份识别码，自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sid;

    /**
     * 学员卡
     */
    @OneToOne
    private Student student;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 账户余额
     */
    private Double accountBalance;

    /**
     * 账户有效期
     */
    private Timestamp validityTime;

    /**
     * 会员资格是否拥有
     */
    private Integer memberValidity;

    /**
     * 会员卡等级
     */
    private Double level;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Timestamp getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Timestamp validityTime) {
        this.validityTime = validityTime;
    }

    public Integer getMemberValidity() {
        return memberValidity;
    }

    public void setMemberValidity(Integer memberValidity) {
        this.memberValidity = memberValidity;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String toCardFormat(){
        String num = this.getSid().toString();
        while(num.length()<7)
            num = "0"+num;
        return num;

    }
}
