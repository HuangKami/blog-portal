package com.kami.blog.model;

import java.io.Serializable;

public class Topic implements Serializable {
	private static final long serialVersionUID = 4281354039645716027L;
	private String topic;
	private int total;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
