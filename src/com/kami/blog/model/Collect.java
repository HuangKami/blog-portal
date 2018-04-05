package com.kami.blog.model;
public class Collect {
    private Integer id;
    private Integer articleId;
    private String userId;
    public Collect() {
        super();
    }
    public Collect(Integer id,Integer articleId,String userId) {
        super();
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return this.articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
