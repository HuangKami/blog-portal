package com.kami.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reply implements Serializable {
	private static final long serialVersionUID = 3378981867182845656L;
	private Integer id;// 主键
	private Timestamp createTime;// 回复时间
	private Integer articleId;// 所属帖子
	private String userId;
	private User user;// 所属用户
	private String content;// 回复内容
	private Integer parentId = 0;// 所属回复

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
