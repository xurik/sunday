package com.alibaba.sunday;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Email:sunday.xur@gmail.com
 * Date: 8/16/12
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class EntityTest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String andAnd;

    public String getAndAnd() {
        return andAnd;
    }

    public void setAndAnd(String andAnd) {
        this.andAnd = andAnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
