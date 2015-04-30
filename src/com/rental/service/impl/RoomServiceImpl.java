package com.rental.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.util.PageBean;
import com.rental.bean.TblRoom;
import com.rental.dao.impl.RoomDaoImpl;
import com.rental.service.RoomService;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {
	@Autowired  
    @Qualifier("roomDao")  
	private RoomDaoImpl roomDao;
	
	public TblRoom addRoom(TblRoom room) {
		return roomDao.addRoom(room);
	}

	public void deleteRoom(int id) {
		TblRoom room = roomDao.findById(id);
		roomDao.deleteRoom(room);
	}

	public List<TblRoom> findAll() {
		return roomDao.findAll();
	}

	public TblRoom findById(int id) {
		return roomDao.findById(id);
	}

	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		return roomDao.findByMixAndPage(map, pageSize, pageNo);
	}

	public void updateRoom(TblRoom room) {
		roomDao.updateRoom(room);
	}

}
