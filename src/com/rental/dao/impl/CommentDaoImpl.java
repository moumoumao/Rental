package com.rental.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rental.bean.TblComment;
import com.rental.bean.TblUser;
import com.rental.dao.CommentDao;
import com.rental.util.PageBean;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	public TblComment addcomment(TblComment comment) {
		super.getHibernateTemplate().save(comment);
		return comment;
	}

	public void deletecomment(TblComment comment) {
		super.getHibernateTemplate().delete(comment);

	}

	@SuppressWarnings("unchecked")
	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		// 查询数据sql
		StringBuffer sql = new StringBuffer("from TblComment o where 1=1");
		Set<String> paramNameSet = map.keySet();
		for(String paramName : paramNameSet){
			if(map.get(paramName).getClass().equals(Integer.class)){
				sql.append(" and o.").append(paramName).append(" =:").append(paramName.replace(".", "_"));
			}else if(map.get(paramName).getClass().equals(String.class)){
				sql.append(" and o.").append(paramName).append(" like :").append(paramName.replace(".", "_"));
			}
		}
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query countQuery =session.createQuery("select count(o) "+sql.toString());
		Query listQuery=session.createQuery(sql.append(" order by o.createDate desc ").toString());
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

}
