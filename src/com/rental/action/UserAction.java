package com.rental.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rental.util.PageBean;
import com.rental.bean.TblUser;
import com.rental.service.impl.UserServiceImpl;
@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"}),
	@Result(name="login",type="redirect",location="/index.jsp"),
	@Result(name="quit",type="redirect",location="/login.jsp")   
  
}) 
public class UserAction extends ActionSupport {
	@Resource(name="userService")
	private UserServiceImpl service;
	private TblUser user;
	private Map<String, Object> root;
	private PageBean pageBean;
	private String startDate;//创建时间范围
	private String endDate;
	/**
	 * 新增用户
	 * @return
	 */
	public String saveUser(){
		root = new HashMap<String, Object>();
		try{
			user.setUserName(user.getLoginName());
			TblUser obj = service.addUser(user);
			root.put("resultCode", 200);
			root.put("msg", "新增用户成功！");
			root.put("data", obj);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
		
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String doLogin(){
		root = new HashMap<String, Object>();
		try{
			user = service.doLogin(user.getLoginName(), user.getUserPwd());
			if(user==null){
				root.put("resultCode", 201);
				root.put("msg", "用户名或密码错误！");
			}else if(user.getLoginFlag()!=1){
				root.put("resultCode", 201);
				root.put("msg", "该用户已被屏蔽！");
			}else{
				ActionContext.getContext().getSession().put("rental_user",user);
				root.put("resultCode", 200);
				root.put("msg", "登录成功！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 判断登录名是否存在
	 * @return
	 */
	public String isExit(){
		root = new HashMap<String, Object>();
		try{
			boolean flag = service.isExit(user.getLoginName());
			if(flag){
				root.put("resultCode", 201);
				root.put("msg", "该用户名已存在！");
			}else{
				root.put("resultCode", 200);
				root.put("msg", "用户名不存在！");
			}
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}

	/**
	 * 修改用户登录权限
	 * @return
	 */
	public String updateLogonFlag(){
		root = new HashMap<String, Object>();
		try{
			service.updateLogonFlag(user.getLoginFlag(),user.getUserId());
			root.put("resultCode", 200);
			root.put("msg", "更新成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}

	/**
	 * 修改用户审核等级
	 * @return
	 */
	public String updateCheckFlag(){
		root = new HashMap<String, Object>();
		try{
			service.updateCheck(user.getCheck_flag(),user.getUserId());
			root.put("resultCode", 200);
			root.put("msg", "更新成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 更新用户信息
	 * @return
	 */
	public String updateInfo(){
		root = new HashMap<String, Object>();
		try{
			TblUser obj = service.updateUser(user);
			root.put("resultCode", 201);
			root.put("msg", "更新成功！");
			ActionContext.getContext().getSession().put("rental_user", obj);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
		
	}
	/**
	 * 根据Id删除用户
	 * @return
	 */
	public String deleteById(){
		root = new HashMap<String, Object>();
		try{
			service.deleteUser(user.getUserId());
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
	 * 退出登录
	 * @return
	 */
	public String doQuit(){
		ActionContext.getContext().getSession().remove("rental_user");
		return "quit";
	}
	/**
	 * 查找所有
	 * @return
	 */
	public String findAll(){
		root = new HashMap<String, Object>();
		try{
			List<TblUser> list = service.findAll();
			root.put("resultCode", 201);
			root.put("msg", "查找成功！");
			root.put("data", list);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 分页查询
	 * @return
	 */
	public String findByMixAndPage(){
		pageBean = service.findByMixAndPage(user,startDate,endDate, pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}

	public TblUser getUser() {
		return user;
	}

	public void setUser(TblUser user) {
		this.user = user;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
