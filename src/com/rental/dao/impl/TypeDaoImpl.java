package com.rental.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rental.util.PageBean;
import com.rental.bean.TblType;
import com.rental.bean.TblUser;
import com.rental.dao.TypeDao;

public class TypeDaoImpl extends HibernateDaoSupport implements TypeDao {

	public TblType addType(TblType type) {
		super.getHibernateTemplate().save(type);
		return type;
	}

	public void deleteType(TblType type) {
		super.getHibernateTemplate().delete(type);
	}

	public TblType findById(int id) {
		return super.getHibernateTemplate().get(TblType.class, id);
	}

	@SuppressWarnings("unchecked")
	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		// 查询数据sql
		StringBuffer sql = new StringBuffer("from TblType o where 1=1");
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
		Query listQuery=session.createQuery(sql.append(" order by createDate ").toString());
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

	public void updateType(TblType Type) {
		super.getHibernateTemplate().merge(Type);
	}

	@SuppressWarnings("unchecked")
	public List<TblType> findAll() {
		return super.getHibernateTemplate().find("from TblType");
	}

}
