package com.rental.service;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblRoom;

public interface RoomService {
	/**
	 * 
	 * @param Room
	 * @return
	 */
	public TblRoom addRoom(TblRoom Room);
	/**
	 * 
	 * @param Room
	 * @return
	 */
	public void updateRoom(TblRoom Room);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void deleteRoom(int id);

	/**
	 * 查找所有信息
	 * 
	 * @return
	 */
	public List<TblRoom> findAll()  ;
	
	/**
	 * 通过Id查找信息
	 * 
	 * @return
	 */
	public TblRoom findById(int id)  ;

	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean  findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo)  ;
	/**
	 * 根据Id查找（普通用户查看，游客查看showRoom）
	 * @return
	 */
	public TblRoom findByIdForUser(int id);
}
