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
/**
 * 收藏
 * @author jy
 *
 */
import com.rental.bean.TblLove;
import com.rental.bean.TblUser;
import com.rental.service.impl.LoveServiceImpl;
import com.rental.util.DateUtil;
import com.rental.util.PageBean;
@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"})
  
})
public class LoveAction extends ActionSupport {
	@Resource(name="loveService")
	private LoveServiceImpl loveService;
	private Map<String, Object> root;
	private TblLove mylove;
	private PageBean pageBean;
	
	/**
	 * 收藏房源
	 * @return
	 */
	public String addLove(){
		root = new HashMap<String, Object>();
		try{
			mylove.setUser((TblUser)ActionContext.getContext().getSession().get("rental_user"));
			mylove.setCreateDate(DateUtil.toYMD(new Date().getTime()));
			TblLove obj = loveService.addLove(mylove);
			root.put("resultCode", 200);
			root.put("msg", "收藏房源成功！");
			root.put("data", obj.getLoveId());
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 取消收藏
	 * @return
	 */
	public String removeLove(){
		root = new HashMap<String, Object>();
		try{
			loveService.deleteLove(mylove);
			root.put("resultCode", 200);
			root.put("msg", "取消收藏成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 查询
	 * @return
	 */
	public String findByMixAndPage(){
		pageBean = loveService.findByMixAndPage(mylove, pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	/**
	 * 是否收藏
	 * @return
	 */
	public String isLove(){
		root = new HashMap<String, Object>();
		try{
			mylove.setUser((TblUser)ActionContext.getContext().getSession().get("rental_user"));
			pageBean = loveService.findByMixAndPage(mylove, -1, -1);
			if(pageBean.getRecordsTotal()>0){
				mylove = (TblLove)pageBean.getAaData().get(0);
				root.put("data",mylove.getLoveId() );
				root.put("resultCode", 200);
				root.put("msg", "该房源已收藏！");
			}else{
				root.put("resultCode", 201);
				root.put("msg", "该房源未收藏！");
			}
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
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
	public TblLove getMylove() {
		return mylove;
	}
	public void setMylove(TblLove mylove) {
		this.mylove = mylove;
	}
	
	
	
}
