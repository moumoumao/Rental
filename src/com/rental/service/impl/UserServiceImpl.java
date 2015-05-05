package com.rental.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.util.PageBean;
import com.rental.bean.TblUser;
import com.rental.dao.UserDao;
import com.rental.service.UserService;
import com.rental.util.DateUtil;
import com.rental.util.StringUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired  
    @Qualifier("userDao")  
	private UserDao userDao;
	
	public TblUser addUser(TblUser user) {
		user.setCreateDate(DateUtil.toYMD(new Date().getTime()));
		return userDao.addUser(user);
	}
	
	public List<TblUser> findAll() {
		return userDao.findAll();
	}

	public TblUser findById(int id) {
		return userDao.findById(id);
	}

	public PageBean findByMixAndPage(TblUser user,String starDate,String endDate, int pageSize,
			int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(user!=null){
			if(!StringUtil.isNullOrEmpty(user.getPhone())){
				map.put("phone", "%"+user.getPhone()+"%");
			}
			if(!StringUtil.isNullOrEmpty(user.getUserName())){
				map.put("userName","%"+ user.getUserName()+"%");
			}
			if(!StringUtil.isNullOrEmpty(user.getUserSex())){
				map.put("userSex",user.getUserSex());
			}
			if(user.getLoginFlag()!=0){
				map.put("loginFlag", user.getLoginFlag());
			}
			if(user.getCheck_flag()!=0){
				map.put("check_flag", user.getCheck_flag());
			}
		}
		if(!StringUtil.isNullOrEmpty(starDate)){
			map.put("startDate", starDate);
		}
		if(!StringUtil.isNullOrEmpty(endDate)){
			map.put("endDate",endDate);
		}
		return userDao.findByMixAndPage(map, pageSize, pageNo);
	}

	public TblUser updateUser(TblUser user) {
		TblUser obj = userDao.findById(user.getUserId());
		if(!StringUtil.isNullOrEmpty(user.getHeadImg())){
			obj.setHeadImg(user.getHeadImg());
		}
		if(!StringUtil.isNullOrEmpty(user.getPhone())){
			obj.setHeadImg(user.getPhone());
		}
		if(!StringUtil.isNullOrEmpty(user.getUserName())){
			obj.setHeadImg(user.getUserName());
		}
		if(!StringUtil.isNullOrEmpty(user.getUserSex())){
			obj.setHeadImg(user.getUserSex());
		}
		if(!StringUtil.isNullOrEmpty(user.getUserPwd())){
			obj.setHeadImg(user.getUserPwd());
		}
		return userDao.updateUser(obj);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(userDao.findById(id));
	}
	
	public boolean isExit(String loginName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		return (userDao.findByMixAndPage(map, -1, -1).getAaData().size()<=0?false:true);
	}

	@SuppressWarnings("unchecked")
	public TblUser doLogin(String loginName, String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		map.put("userPwd", pwd);
		List<TblUser> list = userDao.findByMixAndPage(map, -1, -1).getAaData();
		if(list.size()>0){
			TblUser user = list.get(0);
			user.setLastDate(DateUtil.toYMD(new Date().getTime()));
			return userDao.updateUser(user);
		}
		return null;
	}
	public void updateLogonFlag(int loginFlag,int userId){
		TblUser user = userDao.findById(userId);
		user.setLoginFlag(loginFlag);
		user.setUserId(userId);
		userDao.updateUser(user);
	}

	public void updateCheck(int checkFlag,int userId) {
		TblUser user = userDao.findById(userId);
		user.setCheck_flag(checkFlag);
		user.setUserId(userId);
		userDao.updateUser(user);
	}


}
