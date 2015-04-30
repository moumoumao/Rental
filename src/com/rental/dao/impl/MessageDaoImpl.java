package com.rental.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rental.bean.TblMessage;
import com.rental.bean.TblUser;
import com.rental.dao.MessageDao;
import com.rental.util.PageBean;

public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {

	public TblMessage addMessage(TblMessage message) {
		super.getHibernateTemplate().save(message);
		return message;
	}

	public void deleteMessage(TblMessage message) {
		super.getHibernateTemplate().delete(message);

	}

	public List<TblMessage> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public TblMessage findById(int id) {
		
		return super.getHibernateTemplate().get(TblMessage.class, id);
	}

	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		// 查询数据sql
		StringBuffer sql = new StringBuffer("from TblMessage o where 1=1");
		String order="";
		Set<String> paramNameSet = map.keySet();
		for(String paramName : paramNameSet){
				if(paramName.equals("order")){
					order=" order by o.createDate "+map.get(paramName);
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
		Query listQuery=session.createQuery(sql.append(order).toString());
		// 查询当前页数据
		for (String paramName : paramNameSet) {
			if(!paramName.equals("order")){
				listQuery.setParameter(paramName.replace(".", "_"), map.get(paramName));
				countQuery.setParameter(paramName.replace(".", "_"), map.get(paramName));
			}
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

	public void updateMessage(TblMessage message) {
		super.getHibernateTemplate().merge(message);
	}

}
