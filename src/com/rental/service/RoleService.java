package com.rental.service;

import java.util.List;
import com.rental.util.PageBean;
import com.rental.util.TreeBean;
import com.rental.bean.TblRole;

public interface RoleService {
	/**
	 * 
	 * @param role
	 * @return
	 */
	public TblRole addRole(TblRole role,Integer[] funList);
	/**
	 * 
	 * @param role
	 * @return
	 */
	public void updateRole(TblRole role,Integer[] funList);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteRole(int id);

	/**
	 * 查找所有用户信息
	 * 
	 * @return
	 */
	public List<TblRole> findAll()  ;

	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean  findByMixAndPage(TblRole role,
			int pageSize, int pageNo)  ;
	/**
	 * 是否存在
	 * @param roleName
	 * @return
	 */
	boolean isExit(String roleName);
	/**
	 * 根据Id查找
	 * @return
	 */
	public TblRole findById(int id);
	/**
	 * 查找所有的权限功能
	 * @return
	 */
	public List<TreeBean> findAllFun();
	
}
