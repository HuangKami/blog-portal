package com.kami.blog.service.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kami.blog.dao.UserDao;
import com.kami.blog.model.User;
import com.kami.blog.redis.UserSessionRedis;
import com.kami.blog.common.Assist;
import com.kami.blog.service.UserService;
import com.kami.blog.spring.BloomFilterInit;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.MD5Helper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserSessionRedis userSessionRedis;

	@Override
	public long getUserRowCount(Assist assist) {
		return userDao.getUserRowCount(assist);
	}

	@Override
	public List<User> selectUser(Assist assist) {
		return userDao.selectUser(assist);
	}

	@Override
	public User selectUserByObj(User obj) {
		return userDao.selectUserByObj(obj);
	}

	@Override
	public User selectUserById(String id) {
		return userDao.selectUserById(id);
	}

	@Override
	public User selectUserByName(String name) {
		return userDao.selectUserByName(name);
	}

	@Override
	public User selectUserByEmail(String email) {
		return userDao.selectUserByEmail(email);
	}

	@Override
	public User selectUserByNameOrEmail(User user) {
		return userDao.selectUserByNameOrEmail(user);
	}

	@Override
	public int insertUser(User value) {
		return userDao.insertUser(value);
	}

	@Override
	public int insertNonEmptyUser(User value) {
		return userDao.insertNonEmptyUser(value);
	}

	@Override
	public int insertUserByBatch(List<User> value) {
		return userDao.insertUserByBatch(value);
	}

	@Override
	public int deleteUserById(String id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public int deleteUser(Assist assist) {
		return userDao.deleteUser(assist);
	}

	@Override
	public int updateUserById(User enti) {
		return userDao.updateUserById(enti);
	}

	@Override
	public int updateUser(User value, Assist assist) {
		return userDao.updateUser(value, assist);
	}

	@Override
	public int updateNonEmptyUserById(User enti) {
		return userDao.updateNonEmptyUserById(enti);
	}

	@Override
	public int updateNonEmptyUser(User value, Assist assist) {
		return userDao.updateNonEmptyUser(value, assist);
	}
	

	@Override
    public int deleteUnactiveUser() {
    	return userDao.deleteUnactiveUser();
    }

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String login(HttpServletRequest request, User loginUser, String authCode) {
		String result = "";
		Object loginAuthCode = SessionHelper.getAttribute(request, KeyHelper.LOGIN_AUTHCODE);
		if(loginAuthCode == null) {
			return "验证码过期，请刷新重试";
		}
		// 检查验证码
		if (!StringHelper.equalsIgnoreCase(loginAuthCode.toString(), authCode)) {
			result = "验证码错误";
		} else {
			User user = selectUserByNameOrEmail(loginUser);
			if (user == null) {
				result = "用户名或邮箱不存在，请先注册";
			} else if(!user.isActive()) {
				result = "账号未激活，请前往邮箱激活";
			} else if (!StringHelper.equals(user.getPassword(), MD5Helper.md5(loginUser.getPassword()))) {
				result = "密码错误";
			} else {
				// 校验成功，将用户放进session中
				SessionHelper.setAttribute(request, KeyHelper.USER, user);
				SessionHelper.removeAttribute(request, KeyHelper.LOGIN_AUTHCODE);
				userSessionRedis.saveUserSession(user.getId(), SessionHelper.getSessionId(request));
				result = KeyHelper.SUCCESS;
			}
		}
		return result;
	}

	@Override
	public String checkRegister(HttpServletRequest request, User user, String authCode) {
		if(user == null) {
			return "别闹了";
		}
		if(StringHelper.isEmpty(user.getName())) {
			return "用户名不能为空";
		}
		if(StringHelper.isEmpty(user.getPassword())) {
			return "密码不能为空";
		}
		if(StringHelper.isEmpty(user.getEmail())) {
			return "邮箱不能为空";
		}
		Object registerAuthCode = SessionHelper.getAttribute(request, KeyHelper.REGISTER_AUTHCODE);
		if(registerAuthCode == null) {
			return "请刷新重试";
		}
		if (!StringHelper.equalsIgnoreCase(registerAuthCode.toString(), authCode)) {
			return "验证码错误";
		}
		if(BloomFilterInit.nameFilter.mightContain(user.getName())) {
			return "用户名已被注册";
		}
		if(BloomFilterInit.emailFilter.mightContain(user.getEmail())) {
			return "邮箱已被注册";
		}
		return KeyHelper.SUCCESS;
	}
}