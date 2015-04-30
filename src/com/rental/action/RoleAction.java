package com.rental.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.rental.util.PageBean;
import com.rental.util.TreeBean;
import com.rental.bean.TblRole;
import com.rental.service.impl.RoleServiceImpl;

@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"}),
	@Result(name="quit",type="redirect",location="/login.jsp")   
  
}) 
public class RoleAction extends ActionSupport {
	@Resource(name = "roleService")
	private RoleServiceImpl roleService;
	private TblRole role;
	private Map<String, Object> root;
	private PageBean pageBean;
	private Integer[] funList;
	/**
	 * 新增
	 * @return
	 */
	public String saveRole(){
		root = new HashMap<String, Object>();
		try{
			roleService.addRole(role,funList);
			root.put("resultCode", 200);
			root.put("msg", "添加成功！");
			return "json";
		}catch (Exception e) {
			e.printStackTrace();
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		
	}
	/**
	 * 修改
	 * @return
	 */
	public String updateRole(){
		root = new HashMap<String, Object>();
		try{
			roleService.updateRole(role, funList);
			root.put("resultCode", 200);
			root.put("msg", "更新成功！");
			return "json";
		}catch (Exception e) {
			e.printStackTrace();
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		
	}
	
	/**
	 * 模糊查询
	 * @return
	 */
	public String findByMixAndPage(){
		pageBean = roleService.findByMixAndPage(role, pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	/**
	 * 根据Id删除
	 * @return
	 */
	public String deleteById(){
		root = new HashMap<String, Object>();
		try{
			roleService.deleteRole(role.getRoleId());
			root.put("resultCode", 201);
			root.put("msg", "删除成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 根据Id查找
	 * @return
	 */
	public String findById(){
		root = new HashMap<String, Object>();
		try{
			role = roleService.findById(role.getRoleId());
			root.put("resultCode", 200);
			root.put("msg", "查找成功！");
			root.put("data", role);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	
	public String findAllFun(){
		root = new HashMap<String, Object>();
		try{
			List<TreeBean> list = roleService.findAllFun();
			root.put("resultCode", 200);
			root.put("msg", "查找成功！");
			root.put("data", list );
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	public RoleServiceImpl getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}
	public TblRole getRole() {
		return role;
	}
	public void setRole(TblRole role) {
		this.role = role;
	}
	public Map<String, Object> getRoot() {
		return root;
	}
	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Integer[] getFunList() {
		return funList;
	}
	public void setFunList(Integer[] funList) {
		this.funList = funList;
	}
	
	
}
