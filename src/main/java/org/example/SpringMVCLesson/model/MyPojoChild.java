package org.example.SpringMVCLesson.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MyPojoChild {
    @JsonProperty("DT")
    private Date dt;

    public MyPojoChild(Date dt) {
        this.dt = dt;
    }

    @JsonGetter("DT")
    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
}
