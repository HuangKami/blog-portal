package com.kami.blog.model;

import java.util.ArrayList;
import java.util.List;

public class FollowUser {
	private List<User> followUsers = new ArrayList<>();
	private List<User> followedUsers = new ArrayList<>();
	public List<User> getFollowUsers() {
		return followUsers;
	}
	public void setFollowUsers(List<User> followUsers) {
		this.followUsers = followUsers;
	}
	public List<User> getFollowedUsers() {
		return followedUsers;
	}
	public void setFollowedUsers(List<User> followedUsers) {
		this.followedUsers = followedUsers;
	}

}
