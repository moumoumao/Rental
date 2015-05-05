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
import com.rental.bean.TblComment;
import com.rental.bean.TblUser;
import com.rental.service.impl.CommentServiceImpl;
import com.rental.util.DateUtil;
import com.rental.util.PageBean;

@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"})
})
public class CommentAction extends ActionSupport {

	@Resource(name="comService")
	private CommentServiceImpl msgService;
	private TblComment mycomment;
	private Map<String, Object> root;
	private PageBean pageBean;
	/**
	 * 发送评论
	 */
	public String addComment(){
		root = new HashMap<String, Object>();
		try{
			mycomment.setUser((TblUser)ActionContext.getContext().getSession().get("rental_user"));
			mycomment.setCreateDate(DateUtil.toYMD(new Date().getTime()));
			TblComment obj = msgService.addcomment(mycomment);
			root.put("resultCode", 200);
			root.put("msg", "新增评论成功！");
			root.put("data", obj);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
		
	}

	/**
	 * 删除评论
	 * @param commentId
	 * @return
	 */
	public String  deleteBySender() {
		root = new HashMap<String, Object>();
		try{
			//只需要id和Receive 的userID
			msgService.deleteComment(mycomment);
			root.put("resultCode", 200);
			root.put("msg", "评论删除成功！");
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
		pageBean = msgService.findByMixAndPage(mycomment,pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	
	public TblComment getMycomment() {
		return mycomment;
	}

	public void setMycomment(TblComment mycomment) {
		this.mycomment = mycomment;
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
