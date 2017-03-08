package nju.wjw.entity;

import javax.persistence.*;

/**
 * Created by Jerry Wang on 2017/3/6.
 */
@Entity
@Table(name = "organizationCard")
public class OrganizationCard {

    /**
     * 身份识别码，自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sid;

    /**
     * 学员卡
     */
    @OneToOne(mappedBy = "organizationCard",optional = false,cascade = CascadeType.ALL)
    private Organization organization;

    /**
     * 账号密码
     */
    private String password;


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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String toCardFormat(){
        String num = this.getSid().toString();
        while(num.length()<7)
            num = "0"+num;
        return num;

    }
}
