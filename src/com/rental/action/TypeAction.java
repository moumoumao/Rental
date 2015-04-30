package com.rental.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rental.util.DateUtil;
import com.rental.util.PageBean;
import com.rental.bean.TblType;
import com.rental.bean.TblUser;
import com.rental.service.RoomService;
import com.rental.service.impl.TypeServiceImpl;

@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"}),
	@Result(name="quit",type="redirect",location="/login.jsp")   
  
}) 
public class TypeAction extends ActionSupport {
	@Resource(name="typeService")
	private TypeServiceImpl typeService;
	@Resource(name="roomService")
	private RoomService roomService;
	private TblType Type;
	private Map<String, Object> root;
	private PageBean pageBean;
	
	/**
	 * 新增类型
	 * @return
	 */
	public String addType(){
		root = new HashMap<String, Object>();
		try{
			Type.setCreate((TblUser)ActionContext.getContext().getSession().get("rental_user"));
			Type.setCreateDate(DateUtil.toYMD(new Date().getTime()));
			TblType obj = typeService.addType(Type);
			root.put("resultCode", 200);
			root.put("msg", "新增类型成功！");
			root.put("data", obj);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
	}
	/**
	 * 判断类型名是否存在
	 * @return
	 */
	public String isExit(){
		root = new HashMap<String, Object>();
		try{
			boolean flag = typeService.isExit(Type.getTypeName());
			if(flag){
				root.put("resultCode", 200);
				root.put("msg", "该类型名已存在！");
			}else{
				root.put("resultCode", 201);
				root.put("msg", "类型名不存在！");
			}
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 更新类型信息
	 * @return
	 */
	public String updateInfo(){
		root = new HashMap<String, Object>();
		try{
			typeService.updateType(Type);
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
	 * 根据Id删除类型
	 * @return
	 */
	public String deleteById(){
		root = new HashMap<String, Object>();
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type.typeId", Type.getTypeId());
			int count = roomService.findByMixAndPage(map, -1, -1).getRecordsTotal();
			if(count>0){
				root.put("resultCode", 201);
				root.put("msg", "该类型下已有房源，不可删除！");
			}else{
				typeService.deleteType(Type);
				root.put("resultCode", 200);
				root.put("msg", "删除成功！");
			}
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 查找所有
	 * @return
	 */
	public String findAll(){
		root = new HashMap<String, Object>();
		try{
			List<TblType> list = typeService.findAll();
			root.put("resultCode", 200);
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
	 * 根据Id查找
	 * @return
	 */
	public String findById(){
		root = new HashMap<String, Object>();
		try{
			Type = typeService.findById(Type.getTypeId());
			root.put("resultCode", 201);
			root.put("msg", "查找成功！");
			root.put("date", Type);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	public String findByMixAndPage(){
		pageBean = typeService.findByMixAndPage(Type, pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	public TypeServiceImpl gettypeService() {
		return typeService;
	}
	public void settypeService(TypeServiceImpl typeService) {
		this.typeService = typeService;
	}
	public TblType getType() {
		return Type;
	}
	public void setType(TblType Type) {
		this.Type = Type;
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
	
	
	
	
}
