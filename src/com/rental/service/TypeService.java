package com.rental.service;

import java.util.List;
import com.rental.util.PageBean;
import com.rental.bean.TblType;

public interface TypeService {
	/**
	 * 
	 * @param Type
	 * @return
	 */
	public TblType addType(TblType type);
	/**
	 * 
	 * @param Type
	 * @return
	 */
	public void updateType(TblType type);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteType(TblType type);

	/**
	 * 查找所有信息
	 * 
	 * @return
	 */
	public List<TblType> findAll()  ;
	
	/**
	 * 查找所有信息
	 * 
	 * @return
	 */
	public TblType findById(int id)  ;

	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean  findByMixAndPage(TblType Type,
			int pageSize, int pageNo)  ;
	/**
	 * 是否存在
	 * @param TypeName
	 * @return
	 */
	boolean isExit(String typeName);
}
