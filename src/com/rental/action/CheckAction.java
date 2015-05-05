package com.rental.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rental.bean.TblCheckStream;
import com.rental.bean.TblUser;
import com.rental.service.CheckStreamService;
import com.rental.util.DateUtil;
import com.rental.util.PageBean;

@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"}),
	@Result(name="findById" ,location="/manage/room_detail.jsp"),
	@Result(name="findByIdForCheck" ,location="/manage/room_check.jsp"),
	@Result(name="showRoom" ,location="/manage/room_show.jsp"),
	@Result(name="checkStreamList" ,location="/manage/check_list.jsp")
})

public class CheckAction extends ActionSupport {
	@Resource(name="checkService")
	private CheckStreamService checkService;
	private TblCheckStream check;
	private Map<String, Object> root;
	private PageBean pageBean;
	
	/**
	 * 新增（审核员）
	 * @param stream
	 * @return
	 */
	public String addStreamByCheck(){
		check.setCreate((TblUser)ActionContext.getContext().getSession().get("rental_user"));
		check.setCreateDate(DateUtil.toYMD(new Date().getTime()));
		checkService.addStreamByCheck(check);
		return "json";	
	}
	/**
	 * 提交审核
	 * @param stream
	 * @return
	 */
	public String addStreamByCreate(){
		check.setCreate((TblUser)ActionContext.getContext().getSession().get("rental_user"));
		check.setCreateDate(DateUtil.toYMD(new Date().getTime()));
		checkService.addStreamByCreate(check);
		return "json";
		
	}
	public TblCheckStream getCheck() {
		return check;
	}
	public void setCheck(TblCheckStream check) {
		this.check = check;
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
