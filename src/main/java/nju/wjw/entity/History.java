package nju.wjw.entity;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hisId;

    /**
     * 学生历史记录
     */
    @ManyToOne
    @JoinColumn(name="studentId",nullable = true)
    private Student student;

    @ManyToOne
    @JoinColumn(name="organizationId",nullable = true)
    private Organization organization;

    private Timestamp createdAt;

    private String action;

    public Integer getHisId() {
        return hisId;
    }

    public void setHisId(Integer hisId) {
        this.hisId = hisId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
