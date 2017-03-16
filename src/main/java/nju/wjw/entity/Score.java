package nju.wjw.entity;

import javax.persistence.*;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Entity
@Table(name="score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scoreId;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Student student;

    @ManyToOne
    @JoinColumn(name="cid")
    private Course course;

    private String state;
    /**
     * 成绩登记备注
     */
    private String back;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
