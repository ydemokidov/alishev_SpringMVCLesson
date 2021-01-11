package org.example.SpringMVCLesson.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyPojo {
    @JsonProperty("Id")
    private int id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Child")
    private MyPojoChild child;

    public MyPojo(int id, String name, MyPojoChild child) {
        this.id = id;
        this.name = name;
        this.child = child;
    }

    @JsonGetter(value = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter(value = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter(value = "Child")
    public MyPojoChild getChild() {
        return child;
    }

    public void setChild(MyPojoChild child) {
        this.child = child;
    }
}
