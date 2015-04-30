package com.rental.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rental.util.PageBean;
import com.rental.bean.TblFunction;
import com.rental.bean.TblRole;
import com.rental.bean.TblUser;
import com.rental.dao.RoleDao;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	public TblRole addRole(TblRole role) {
		super.getHibernateTemplate().save(role);
		return role;
	}

	public void deleteUser(TblRole role) {
		super.getHibernateTemplate().delete(role);
	}

	@SuppressWarnings("unchecked")
	public List<TblRole> findAll() {
		return super.getHibernateTemplate().find("from TblRole");
	}

	@SuppressWarnings("unchecked")
	public List<TblFunction> findAllFun() {
		return super.getHibernateTemplate().find("from TblFunction");
	}

	public TblRole findById(int roleId) {
		return super.getHibernateTemplate().get(TblRole.class, roleId);
	}

	@SuppressWarnings("unchecked")
	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		// 查询数据sql
		StringBuffer sql = new StringBuffer("from TblRole o where 1=1");
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
		Query listQuery=session.createQuery(sql.toString());
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

	public void updateRole(TblRole role) {
		super.getHibernateTemplate().merge(role);
	}

	public TblFunction findFunById(int funId) {
		return super.getHibernateTemplate().get(TblFunction.class, funId);
	}

}
