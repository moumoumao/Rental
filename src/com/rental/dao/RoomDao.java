package com.rental.dao;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblRoom;


/**
 * 房源数据库操作接口
 * @author jy
 *
 */
public interface RoomDao {
	/**
	 * 新增房源
	 * @param Room
	 * @return
	 */
	public TblRoom addRoom(TblRoom room);
	/**
	 * 删除房源
	 * @param RoomId
	 * @return
	 */
	public void deleteRoom(TblRoom room);
	/**
	 * 修改房源信息
	 * @param Room
	 * @return
	 */
	public void updateRoom(TblRoom room);
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
	public  TblRoom  findById(int id);
	/**
	 * 查找所有
	 * @return
	 */
	public List<TblRoom> findAll();
}
