package com.rental.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.util.PageBean;
import com.rental.util.TreeBean;
import com.rental.bean.TblFunction;
import com.rental.bean.TblPower;
import com.rental.bean.TblRole;
import com.rental.dao.RoleDao;
import com.rental.service.RoleService;
import com.rental.util.StringUtil;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired  
    @Qualifier("roleDao")  
	private RoleDao roleDao;

	public TblRole addRole(TblRole role,Integer[] funList) {
		if(funList!=null){
			for(int funId:funList){
				TblPower power = new TblPower();
				power.setFun(roleDao.findFunById(funId));
				power.setRole(role);
				role.getPowerSet().add(power);
			}
		}
		
		return roleDao.addRole(role);
	}

	public void deleteRole(int id) {
		TblRole role = roleDao.findById(id);
		roleDao.deleteUser(role);
	}

	public List<TblRole> findAll() {
		return roleDao.findAll();
	}

	public PageBean findByMixAndPage(TblRole role, int pageSize,
			int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtil.isNullOrEmpty(role.getRoleName())){
			map.put("roleName", "%"+role.getRoleName()+"%");
		}
		if(!StringUtil.isNullOrEmpty(role.getRoleContent())){
			map.put("roleContent","%"+ role.getRoleContent()+"%");
		}
		return roleDao.findByMixAndPage(map, pageSize, pageNo);
	}

	public boolean isExit(String roleName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		return (roleDao.findByMixAndPage(map, -1, -1).getRecordsTotal()>0)?true:false;
	}
/**
 * 修改
 */
	public void updateRole(TblRole role,Integer[] funList) {
		TblRole obj = roleDao.findById(role.getRoleId());
		if(!StringUtil.isNullOrEmpty(role.getRoleName())){
			obj.setRoleName(role.getRoleName());
		}
		if(!StringUtil.isNullOrEmpty(role.getRoleContent())){
			obj.setRoleContent(role.getRoleContent());
		}
		if(funList!=null){
			Set<TblPower> set = new HashSet<TblPower>();
			for(int funId:funList){
				TblPower power = new TblPower();
				power.setFun(roleDao.findFunById(funId));
				power.setRole(role);
				set.add(power);
			}
			role.setPowerSet(set);
		}
		roleDao.updateRole(role);
	}
	/**
	 * 查找所有的权限功能
	 * @return
	 */
	public List<TreeBean> findAllFun() {
		
		List<TreeBean> treeList = new ArrayList<TreeBean>();
		TreeBean obj;
		Map<String, List<TreeBean> > map = new HashMap<String, List<TreeBean> >();
		for(TblFunction fun :roleDao.findAllFun()){
			String key = fun.getFatherName();//父标题
			String funName = fun.getFunName();//标题
			String targ = new String(""+fun.getFunId());//标题
			if(map.containsKey(key)){
				obj = new TreeBean(funName,targ);
				map.get(key).add(obj);
			}else{
				List<TreeBean> fatherList = new ArrayList<TreeBean>();
				fatherList.add(new TreeBean(funName,targ));
				map.put(key, fatherList);
			}
		}
		for(String key : map.keySet()){
			TreeBean tree = new TreeBean(key);
			tree.setNodes(map.get(key)); 
			treeList.add(tree);
		}
		
		return treeList;
	}
	/**
	 * 根据Id查找
	 * @return
	 */
	public TblRole findById(int id) {
		return roleDao.findById(id);
	}

}
