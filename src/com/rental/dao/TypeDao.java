package com.rental.dao;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblType;


/**
 * 类型数据库操作接口
 * @author jy
 *
 */
public interface TypeDao {
	/**
	 * 新增类型
	 * @param Type
	 * @return
	 */
	public TblType addType(TblType type);
	/**
	 * 删除类型
	 * @param TypeId
	 * @return
	 */
	public void deleteType(TblType type);
	/**
	 * 修改类型信息
	 * @param Type
	 * @return
	 */
	public void updateType(TblType type);
	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	PageBean  findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo)  ;
	/**
	 * 根据Id查找
	 * @return
	 */
	public  TblType  findById(int id);
	/**
	 * 查找所有
	 * @return
	 */
	public List<TblType> findAll();
}
