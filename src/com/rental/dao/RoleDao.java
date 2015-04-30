package com.rental.dao;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblFunction;
import com.rental.bean.TblRole;

/**
 * 角色数据接口
 * @author jy
 *
 */
public interface RoleDao {

	/**
	 * 新增
	 * @param role
	 * @throws Exception
	 */
	public TblRole addRole(TblRole role) ;
	/**
	 * 更新
	 * @param role
	 * @
	 */
	public void updateRole(TblRole role) ;
	/**
	 * 删除
	 * @param roleId
	 * @
	 */
	public void deleteUser(TblRole role) ;
	/**
	 * 根据Id查找
	 * @param roleId
	 * @return
	 */
	public TblRole findById(int roleId) ;
	/**
	 * 查找所有角色详细
	 * @return
	 * @
	 */
	public List<TblRole> findAll() ;
	
	/**
	 * 查找所有权限详细
	 * @return
	 * @
	 */
	public List<TblFunction> findAllFun() ;
	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo) ;
	/**
	 * 根据对照表iD查找对照信息
	 * @return
	 */
	public TblFunction findFunById(int funId);

}
