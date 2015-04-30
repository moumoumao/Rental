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
import com.rental.bean.TblArea;
import com.rental.bean.TblUser;
import com.rental.service.RoomService;
import com.rental.service.impl.AreaServiceImpl;

 
@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"})
  
})
public class AreaAction extends ActionSupport {
	@Resource(name="areaService")
	private AreaServiceImpl areaService;
	@Resource(name="roomService")
	private RoomService roomService;
	private TblArea area;
	private Map<String, Object> root;
	private PageBean pageBean;
	
	/**
	 * 新增区域
	 * @return
	 */
	public String addArea(){
		root = new HashMap<String, Object>();
		try{
			area.setCreate((TblUser)ActionContext.getContext().getSession().get("rental_user"));
			area.setCreateDate(DateUtil.toYMD(new Date().getTime()));
			TblArea obj = areaService.addArea(area);
			root.put("resultCode", 200);
			root.put("msg", "新增区域成功！");
			root.put("data", obj);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
	}
	/**
	 * 判断区域名是否存在
	 * @return
	 */
	public String isExit(){
		root = new HashMap<String, Object>();
		try{
			boolean flag = areaService.isExit(area.getAreaName());
			if(flag){
				root.put("resultCode", 200);
				root.put("msg", "该区域名已存在！");
			}else{
				root.put("resultCode", 201);
				root.put("msg", "区域名不存在！");
			}
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 更新区域信息
	 * @return
	 */
	public String updateInfo(){
		root = new HashMap<String, Object>();
		try{
			areaService.updateArea(area);
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
	 * 根据Id删除区域
	 * @return
	 */
	public String deleteById(){
		root = new HashMap<String, Object>();
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type.typeId", area.getAreaId());
			int count = roomService.findByMixAndPage(map, -1, -1).getRecordsTotal();
			if(count>0){
				root.put("resultCode", 201);
				root.put("msg", "该区域下已有房源，不可删除！");
			}else{
				areaService.deleteArea(area);
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
			List<TblArea> list = areaService.findAll();
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
			area = areaService.findById(area.getAreaId());
			root.put("resultCode", 201);
			root.put("msg", "查找成功！");
			root.put("date", area);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	public String findByMixAndPage(){
		pageBean = areaService.findByMixAndPage(area, pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	public AreaServiceImpl getAreaService() {
		return areaService;
	}
	public void setAreaService(AreaServiceImpl areaService) {
		this.areaService = areaService;
	}
	public TblArea getArea() {
		return area;
	}
	public void setArea(TblArea area) {
		this.area = area;
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
