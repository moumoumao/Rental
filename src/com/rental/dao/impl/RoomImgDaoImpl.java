package com.rental.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rental.bean.TblRoomImage;
import com.rental.dao.RoomImgDao;

public class RoomImgDaoImpl extends HibernateDaoSupport implements RoomImgDao {

	public void deleteRoom(TblRoomImage img){
		super.getHibernateTemplate().delete(img);
	}


	@SuppressWarnings("unchecked")
	public TblRoomImage findByPath(String path) {
		List<TblRoomImage> list = super.getHibernateTemplate().find("from TblRoomImage where imgPath ='"+path+"'");
		return (list.size()==1)?list.get(0):null;
	}

}
