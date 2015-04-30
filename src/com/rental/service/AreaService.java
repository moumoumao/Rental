package com.rental.service;

import java.util.List;
import com.rental.util.PageBean;
import com.rental.bean.TblArea;

public interface AreaService {
	/**
	 * 
	 * @param area
	 * @return
	 */
	public TblArea addArea(TblArea area);
	/**
	 * 
	 * @param area
	 * @return
	 */
	public void updateArea(TblArea area);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteArea(TblArea area);

	/**
	 * 查找所有信息
	 * 
	 * @return
	 */
	public List<TblArea> findAll()  ;
	
	/**
	 * 查找所有信息
	 * 
	 * @return
	 */
	public TblArea findById(int id)  ;

	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean  findByMixAndPage(TblArea area,
			int pageSize, int pageNo)  ;
	/**
	 * 是否存在
	 * @param areaName
	 * @return
	 */
	boolean isExit(String areaName);
}
