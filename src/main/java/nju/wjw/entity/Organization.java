package nju.wjw.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer oid;

    private String name;

    private String email;

    private String description;

    private Double money;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    private OrganizationCard organizationCard;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "oid")
    private List<Course> courseList;

    @OneToOne
    @JoinColumn(name="aid")
    private Account account;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public OrganizationCard getOrganizationCard() {
        return organizationCard;
    }

    public void setOrganizationCard(OrganizationCard organizationCard) {
        this.organizationCard = organizationCard;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
