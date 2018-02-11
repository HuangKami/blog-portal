package com.kami.blog.model;
public class Blackip {
    private Integer id;
    private String ip;
    public Blackip() {
        super();
    }
    public Blackip(Integer id,String ip) {
        super();
        this.id = id;
        this.ip = ip;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
