package com.rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rental.bean.TblRoomImage;
import com.rental.dao.impl.RoomImgDaoImpl;
import com.rental.service.RoomImgService;
@Service("imgService")
public class RoomImgServiceImpl implements RoomImgService {
	@Autowired  
    @Qualifier("imgDao")  
	private RoomImgDaoImpl imgDao;
	public void deleteRoomImg(String path) {
		TblRoomImage img = imgDao.findByPath(path);
		imgDao.deleteRoom(img);
	}
}
