package com.rental.dao;

import com.rental.bean.TblRoomImage;


/**
 * 房源图片数据库操作接口
 * @author jy
 *
 */
public interface RoomImgDao {
	
	public void deleteRoom(TblRoomImage img);
	/**
	 * 根据图片地址查找（唯一）
	 * @param path
	 * @return
	 */
	public  TblRoomImage  findByPath(String path);
	
	
}
