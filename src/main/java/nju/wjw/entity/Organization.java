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

    @OneToMany
    @JoinColumn(name = "oid")
    private List<Course> courseList;

    @OneToOne
    @JoinColumn(name="aid")
    private Account account;

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
