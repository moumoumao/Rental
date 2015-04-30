package com.rental.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rental.bean.TblMessage;
import com.rental.bean.TblUser;
import com.rental.service.impl.MsgServiceImpl;
import com.rental.util.DateUtil;
import com.rental.util.PageBean;

@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"})
})
public class MsgAction extends ActionSupport {

	@Resource(name="msgService")
	private MsgServiceImpl msgService;
	private TblMessage message;
	private Map<String, Object> root;
	private PageBean pageBean;
	private String order;//创建时间排序
	/**
	 * 发送消息
	 */
	public String sendMsg(){
		root = new HashMap<String, Object>();
		try{
			message.setSend((TblUser)ActionContext.getContext().getSession().get("rental_user"));
			message.setCreateDate(DateUtil.toYMD(new Date().getTime()));
			TblMessage obj = msgService.addMessage(message);
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
	 * 接收方删除消息
	 * @param MessageId
	 * @return
	 */
	public String deleteByReceive() {
		root = new HashMap<String, Object>();
		try{
			//只需要id和Receive 的userID
			msgService.deleteByReceive(message);
			root.put("resultCode", 200);
			root.put("msg", "消息删除成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
	}
	/**
	 * 发送方删除消息
	 * @param MessageId
	 * @return
	 */
	public String  deleteBySender() {
		root = new HashMap<String, Object>();
		try{
			//只需要id和Receive 的userID
			msgService.deleteBySender(message);
			root.put("resultCode", 200);
			root.put("msg", "消息删除成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
	}
	
	/**
	 * 模糊组合查询
	 * @return
	 */
	public String findByMixAndPage(){
		pageBean = msgService.findByMixAndPage(message, order,pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	
	public TblMessage getMessage() {
		return message;
	}
	public void setMessage(TblMessage message) {
		this.message = message;
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
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
