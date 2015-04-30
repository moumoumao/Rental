package com.rental.dao;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblArea;


/**
 * 地区数据库操作接口
 * @author jy
 *
 */
public interface AreaDao {
	/**
	 * 新增地区
	 * @param area
	 * @return
	 */
	public TblArea addArea(TblArea area);
	/**
	 * 删除地区
	 * @param areaId
	 * @return
	 */
	public void deleteArea(TblArea area);
	/**
	 * 修改地区信息
	 * @param area
	 * @return
	 */
	public void updateArea(TblArea area);
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
	public  TblArea  findById(int id);
	/**
	 * 查找所有
	 * @return
	 */
	public List<TblArea> findAll();
}
