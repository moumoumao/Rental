package com.rental.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rental.util.PageBean;
import com.rental.bean.TblRoom;
import com.rental.bean.TblUser;
import com.rental.dao.RoomDao;

public class RoomDaoImpl extends HibernateDaoSupport implements RoomDao {

	public TblRoom addRoom(TblRoom room) {
		super.getHibernateTemplate().save(room);
		return room;
	}

	public void deleteRoom(TblRoom room) {
		super.getHibernateTemplate().delete(room);
	}

	public TblRoom findById(int id) {
		return super.getHibernateTemplate().get(TblRoom.class, id);
	}

	@SuppressWarnings("unchecked")
	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		// 查询数据sql
		StringBuffer sql = new StringBuffer("from TblRoom o where 1=1");
		Set<String> paramNameSet = map.keySet();
		for(String paramName : paramNameSet){
			if(paramName.equals("startDate")){//房源建立时间
				sql.append(" and o.buildDate >=:").append(paramName);
			}else if(paramName.equals("endDate")){
				sql.append(" and o.buildDate <=:").append(paramName);
			}else if(paramName.equals("minPrice")){//价格
				sql.append(" and o.price >=:").append(paramName);
			}else if(paramName.equals("maxPrice")){
				sql.append(" and o.price <=:").append(paramName);
			}else if(paramName.equals("updateStart")){//信息更新时间
				sql.append(" and o.updateDate >=:").append(paramName);
			}else if(paramName.equals("updateEnd")){
				sql.append(" and o.updateDate <=:").append(paramName);
			}else{
				if(map.get(paramName).getClass().equals(Integer.class)){
					sql.append(" and o.").append(paramName).append(" =:").append(paramName.replace(".", "_"));
				}else if(map.get(paramName).getClass().equals(String.class)){
					sql.append(" and o.").append(paramName).append(" like :").append(paramName.replace(".", "_"));
				}
			}
		}
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query countQuery =session.createQuery("select count(o) "+sql.toString());
		Query listQuery=session.createQuery(sql.append(" order by createDate desc").toString());
		// 查询当前页数据
		for (String paramName : paramNameSet) {
			listQuery.setParameter(paramName.replace(".", "_"), map.get(paramName));
			countQuery.setParameter(paramName.replace(".", "_"), map.get(paramName));
		}
		if(pageSize!=-1&&pageNo!=-1){//不分页
			listQuery.setFirstResult((pageNo - 1) * pageSize);
			listQuery.setMaxResults(pageSize);
		}
		List<TblUser> list = listQuery.list();
		Long count = ((Long) countQuery.uniqueResult());
		PageBean pageBean =new PageBean(list, count.intValue(),pageNo,pageSize);
		return pageBean;
	}

	public void updateRoom(TblRoom Room) {
		super.getHibernateTemplate().merge(Room);
	}

	@SuppressWarnings("unchecked")
	public List<TblRoom> findAll() {
		return super.getHibernateTemplate().find("from TblRoom");
	}

}
