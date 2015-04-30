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
import com.rental.util.PageBean;
import com.rental.bean.TblArea;
import com.rental.bean.TblRoom;
import com.rental.bean.TblUser;
import com.rental.service.RoomService;
import com.rental.util.DateUtil;
import com.rental.util.StringUtil;

 
@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"}),
	@Result(name="findById" ,location="/manage/room_detail.jsp"),
	@Result(name="showRoom" ,location="/manage/room_show.jsp"),
	@Result(name="checkStreamList" ,location="/manage/check_list.jsp")
})
public class RoomAction extends ActionSupport {
	@Resource(name="roomService")
	private RoomService roomService;
	private TblRoom room;
	private Map<String, Object> root;
	private PageBean pageBean;
	
	private String minPrice;//价格范围
	private String maxPrice;
	private String startDate;//房源建立时间范围
	private String endDate;
	private String updateStart;//更新时间范围
	private String updateEnd;
	
	/**
	 * 新增房源
	 * @return
	 */
	public String addRoom(){
		root = new HashMap<String, Object>();
		try{
			TblUser user = (TblUser)ActionContext.getContext().getSession().get("rental_user");
			String time = DateUtil.toYMD(new Date().getTime());
			room.setCreate(user);
			room.setCreateDate(time);
			room.setUpdate(user);
			room.setUpdateDate(time);
			TblRoom obj = roomService.addRoom(room);
			root.put("resultCode", 200);
			root.put("msg", "新增房源成功！");
			root.put("data", obj);
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
	}
	/**
	 * 更新房源信息
	 * @return
	 */
	public String updateInfo(){
		root = new HashMap<String, Object>();
		try{
			TblRoom obj = roomService.findById(room.getRoomId());
			if(StringUtil.isNullOrEmpty(room.getCheckFlag())){
				//审核状态（未审核，一级审核，二级审核）
				room.setCheckFlag(obj.getCheckFlag());
			}
			if(StringUtil.isNullOrEmpty(room.getCheckState())){
				//审核结果（通过、拒绝、未审核、审核中）
				room.setCheckState(obj.getCheckState());
			}
			
			TblUser user = (TblUser)ActionContext.getContext().getSession().get("rental_user");
			String time=DateUtil.toYMD(new Date().getTime());
			room.setUpdate(user);
			room.setUpdateDate(time);
			roomService.updateRoom(room);
			root.put("resultCode", 201);
			root.put("msg", "更新成功！");
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		return "json";
		
	}
	/**
	 * 根据Id删除房源
	 * @return
	 */
	public String deleteById(){
		root = new HashMap<String, Object>();
		try{
			roomService.deleteRoom(room.getRoomId());
			root.put("resultCode", 200);
			root.put("msg", "删除成功！");
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
			List<TblRoom> list = roomService.findAll();
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
	 * 根据Id查找（返回页面roomDetail）
	 * @return
	 */
	public String findById(){
		room = roomService.findById(room.getRoomId());
		return "findById";
	}
	/**
	 * 根据Id查找（普通用户查看，游客查看showRoom）
	 * @return
	 */
	public String findByIdForUser(){
		room = roomService.findById(room.getRoomId());
		return "showRoom";
	}
	/**
	 * 去审核流程页面
	 * @return
	 */
	public String toCheckStreamView(){
		room = roomService.findById(room.getRoomId());
		return "checkStreamList";
	}
	
	/**
	 * 根据Id查找（返回json）
	 * @return
	 */
	public String showRoomById(){
		root = new HashMap<String, Object>();
		try{
			room = roomService.findById(room.getRoomId());
			root.put("resultCode", 200);
			root.put("msg", "查找成功！");
			root.put("data", room);
			return "json";
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return "json";
		}
		
	}
	/**
	 * 组合、模糊、分页查询
	 * @return
	 */
	public String findByMixAndPage(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(room!=null){
			if(!StringUtil.isNullOrEmpty(room.getRoomTitle())){
				map.put("roomTitle","%"+ room.getRoomTitle()+"%");
			}
			if(!StringUtil.isNullOrEmpty(room.getRoomContent())){
				map.put("roomContent","%"+ room.getRoomContent()+"%");
			}
			if(!StringUtil.isNullOrEmpty(room.getCompounds())){ //小区名称
				map.put("compounds","%"+ room.getCompounds()+"%");
			}
			if(!StringUtil.isNullOrEmpty(room.getAddress())){//详细地址
				map.put("address","%"+ room.getAddress()+"%");
			}
			if(room.getArea()!=null){
				TblArea area =room.getArea();
				if(area.getAreaId()!=0){
					map.put("area.areaID", area.getAreaName());
				}
			}
			if(room.getType()!=null){
				if(room.getType().getTypeId()!=0){
					map.put("type.typeId",room.getType().getTypeId());
				}
			}
			if(!StringUtil.isNullOrEmpty(minPrice)){//租金
				map.put("minPrice",minPrice);
			}
			if(!StringUtil.isNullOrEmpty(maxPrice)){
				map.put("maxPrice",maxPrice);
			}
			if(!StringUtil.isNullOrEmpty(startDate)){//房屋创建时间
				map.put("startDate",startDate);
			}
			if(!StringUtil.isNullOrEmpty(endDate)){
				map.put("endDate",endDate);
			}
			if(!StringUtil.isNullOrEmpty(updateStart)){
				map.put("updateStart",updateStart);
			}
			if(!StringUtil.isNullOrEmpty(updateEnd)){
				map.put("updateEnd",updateEnd);
			}
			if(room.getRentType()!=0){//出租类型(1.整租2.合租)
				map.put("rentType", room.getRentType());
			}
			if(room.getRoomNum()!=0){//室
				map.put("roomNum", room.getRoomNum());
			}
			
			if(room.getHallNum()!=0){//厅
				map.put("hallNum", room.getHallNum());
			}
			if(room.getToiletNum()!=0){//卫
				map.put("toiletNum", room.getToiletNum());
			}
			if(!StringUtil.isNullOrEmpty(room.getCheckFlag())){
				//审核状态（未审核，一级审核，二级审核）
				map.put("checkFlag",  room.getCheckFlag());
			}
			if(!StringUtil.isNullOrEmpty(room.getCheckState())){
				//审核结果（通过、拒绝、未审核、审核中）
				map.put("checkState", room.getCheckState());
			}
		}
		
		pageBean = roomService.findByMixAndPage(map, pageBean.getPageSize(), pageBean.getPageNo());
		return "page";
	}
	public TblRoom getRoom() {
		return room;
	}
	public void setRoom(TblRoom room) {
		this.room = room;
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
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
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
	public String getUpdateStart() {
		return updateStart;
	}
	public void setUpdateStart(String updateStart) {
		this.updateStart = updateStart;
	}
	public String getUpdateEnd() {
		return updateEnd;
	}
	public void setUpdateEnd(String updateEnd) {
		this.updateEnd = updateEnd;
	}
	public RoomService getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
}
