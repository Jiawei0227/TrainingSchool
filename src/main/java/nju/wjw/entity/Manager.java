package nju.wjw.entity;

import javax.persistence.*;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Entity
@Table(name = "table")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
