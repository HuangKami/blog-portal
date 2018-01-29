package com.kami.blog.entity;
public class User {
    private String name;
    private String pass;
    public User() {
        super();
    }
    public User(String name,String pass) {
        super();
        this.name = name;
        this.pass = pass;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
