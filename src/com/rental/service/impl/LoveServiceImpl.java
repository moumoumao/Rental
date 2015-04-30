package com.rental.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rental.bean.TblLove;
import com.rental.bean.TblRoom;
import com.rental.bean.TblUser;
import com.rental.dao.LoveDao;
import com.rental.service.LoveService;
import com.rental.util.PageBean;
import com.rental.util.StringUtil;
@Service("loveService")
public class LoveServiceImpl implements LoveService {

	@Autowired
	@Qualifier("loveDao")
	private LoveDao loveDao;
	/**
	 * 新增收藏
	 */
	public TblLove addLove(TblLove love) {
		return loveDao.addLove(love);
	}

	/**
	 * 取消收藏
	 */
	public void deleteLove(TblLove love){
		loveDao.deleteLove(love);
	}

	/**
	 * 组合查询
	 */
	public PageBean findByMixAndPage(TblLove love, int pageSize,
			int pageNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(love!=null){
			TblRoom room = love.getRoom();
			TblUser user = love.getUser();
			if(user!=null){
				if(user.getUserId()!=0){
					map.put("user.userId", user.getUserId());
				}
			}
			if(room!=null){
				if(room.getRoomId()!=0){
					map.put("room.roomId", room.getRoomId());
				}
				if(!StringUtil.isNullOrEmpty(room.getRoomTitle())){
					map.put("room.roomTitle", "%"+room.getRoomTitle()+"%");
				}
				if(!StringUtil.isNullOrEmpty(room.getRoomContent())){
					map.put("room.roomContent","%"+ room.getRoomContent()+"%");
				}
			}
			
		}
		return loveDao.findByMixAndPage(map, pageSize, pageNo);
	}

}
