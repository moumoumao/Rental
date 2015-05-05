package com.rental.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.bean.TblComment;
import com.rental.bean.TblRoom;
import com.rental.bean.TblUser;
import com.rental.dao.CommentDao;
import com.rental.service.CommentService;
import com.rental.util.PageBean;
import com.rental.util.StringUtil;
@Service("comService")
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
	@Qualifier("comDao")
	private CommentDao comDao;
	public TblComment addcomment(TblComment comment) {
		return comDao.addcomment(comment);
	}

	public void deleteComment(TblComment comment) {
		comDao.deletecomment(comment);
	}

	public PageBean findByMixAndPage(TblComment comment,
			int pageSize, int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(comment!=null){
			TblUser user = comment.getUser();
			TblRoom room = comment.getRoom();
			if(!StringUtil.isNullOrEmpty(comment.getComContent())){
				map.put("comContent", "%"+comment.getComContent()+"%");
			}
			if(user!=null){
				if(user.getUserId()!=0){
					map.put("user.userId", user.getUserId());
				}
			}
			if(room!=null){
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
		}
		return comDao.findByMixAndPage(map, pageSize, pageNo);
	}

}
