package nju.wjw.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jerry Wang on 2017/2/13.
 */
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sid;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    private StudentCard studentCard;

    private String name;

    private String email;

    private Integer age;

    @OneToMany(mappedBy = "student")
    private List<History> historyList;

    @ManyToMany
    @JoinTable(name="score",joinColumns = @JoinColumn(name="sid"),inverseJoinColumns = @JoinColumn(name="cid"))
    private List<Course> courses;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourseList(List<Course> courses) {
        this.courses = courses;
    }
}
