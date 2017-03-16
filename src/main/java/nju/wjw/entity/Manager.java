package nju.wjw.entity;

import javax.persistence.*;

/**
 * Created by Jerry Wang on 2017/2/28.
 */
@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toCardFormat(){
        String num = this.getId().toString();
        while(num.length()<7)
            num = "0"+num;
        return num;

    }
}
