package com.rental.dao;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblUser;
/**
 * 用户数据接口
 * @author jy
 *
 */
public interface UserDao {
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public TblUser addUser(TblUser user) ;
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
	 * 根据Id删除用户
	 * 
	 * @param userId
	 * @return
	 */
	public void deleteUser(TblUser user)  ;

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
	public PageBean  findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo)  ;

}
