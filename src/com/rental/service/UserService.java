package com.rental.service;

import java.util.List;
import com.rental.util.PageBean;
import com.rental.bean.TblUser;

public interface UserService {
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	TblUser addUser(TblUser user) ;
	/**
	 * 查找单个用户
	 * 
	 * @param id
	 * @return
	 */
	public TblUser findById(int id)  ;
	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	public TblUser updateUser(TblUser user)  ;
	/**
	 * 更新用户审核等级
	 * 
	 * @param user
	 * @return
	 */
	public void updateCheck(int checkFlag,int userId)  ;

	/**
	 * 根据Id删除用户
	 * 
	 * @param userId
	 * @return
	 */
	public void deleteUser(int id)  ;

	/**
	 * 查找所有用户信息
	 * 
	 * @return
	 */
	public List<TblUser> findAll()  ;

	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean  findByMixAndPage(TblUser user,String starDate,String endDate,
			int pageSize, int pageNo)  ;
	/**
	 * 是否存在
	 * @param loginName
	 * @return
	 */
	boolean isExit(String loginName);
	
	/**
	 * 登录
	 * @param loginName
	 * @return
	 */
	TblUser doLogin(String loginName,String pwd);
	/**
	 * 修改登录权限
	 * @param loginFlag
	 */
	void updateLogonFlag(int loginFlag,int userId);
}
