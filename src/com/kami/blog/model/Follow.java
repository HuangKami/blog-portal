package com.kami.blog.model;
public class Follow {
    private Integer id;
    private String follower;
    private String beFollowed;
    public Follow() {
        super();
    }
    public Follow(Integer id,String follower,String beFollowed) {
        super();
        this.id = id;
        this.follower = follower;
        this.beFollowed = beFollowed;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFollower() {
        return this.follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getBeFollowed() {
        return this.beFollowed;
    }

    public void setBeFollowed(String beFollowed) {
        this.beFollowed = beFollowed;
    }

}
