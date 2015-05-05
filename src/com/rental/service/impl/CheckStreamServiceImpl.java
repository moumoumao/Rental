package com.rental.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.bean.TblCheckStream;
import com.rental.bean.TblRoom;
import com.rental.dao.CheckStreamDao;
import com.rental.dao.impl.RoomDaoImpl;
import com.rental.service.CheckStreamService;
import com.rental.util.PageBean;
@Service("checkService")
@Transactional
public class CheckStreamServiceImpl implements CheckStreamService {
	@Autowired  
    @Qualifier("roomDao")  
	private RoomDaoImpl roomDao;
	
	@Autowired  
    @Qualifier("checkDao")  
	private CheckStreamDao checkDao;
	public TblCheckStream addStream(TblCheckStream stream) {
		
		return null;
	}

	public void deleteStream(TblCheckStream stream) {
		// TODO Auto-generated method stub

	}

	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public TblCheckStream addStreamByCheck(TblCheckStream stream) {
		checkDao.addStream(stream);
		TblRoom room = roomDao.findById(stream.getRoom().getRoomId());
		room.setCheckFlag((stream.getCreate().getCheck_flag()==1)?"一级审核":"二级审核");
		room.setCheckState(stream.getCheckState());
		return stream;
	}

	public TblCheckStream addStreamByCreate(TblCheckStream stream) {
		stream.setCheckFlag("提交审核");
		checkDao.addStream(stream);
		TblRoom room = roomDao.findById(stream.getRoom().getRoomId());
		room.setCheckFlag("提交审核");
		room.setCheckState("");
		return null;
	}

}
