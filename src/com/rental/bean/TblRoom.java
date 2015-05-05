package com.rental.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 房源 
 * @author jy
 *
 */
@Entity
@Table(name="t_room",catalog="rental")
public class TblRoom {
	@Id
	@GeneratedValue
	@Column(name="room_id")
	private  int roomId;
	
	@Column(name="room_title")
	private String roomTitle;//标题
	
	private String compounds; //小区名称
	
	@ManyToOne
	@JoinColumn(name="area_id")
	private TblArea area;//所处区域

	@Column(name="room_address")
	private String address;//详细地址
	
	private int price;//租金
	
	@Column(name ="rent_type")
	private int rentType;//出租类型(1.整租2.合租)
	
	@Column(name = "build_date")
	private String buildDate;//房屋创建时间
	
	@Column(name="room_content")
	private String roomContent;//详细介绍
	
	@Column(name="room_num")
	private int roomNum;//室
	
	@Column(name="hall_num")
	private int hallNum;//厅
	
	@Column(name="toilet_num")
	private int toiletNum;//卫
	
	@Column(name="kitchen_num")
	private int kitchenNum;//厨
	
	@Column(name="room_area")
	private String roomArea;//面积（平方米）
	
	private String floor;//楼层（19/20）
	@ManyToOne
	@JoinColumn(name="type_id")
	private TblType type;//类型（公寓）
	
	@Column(name="user_name")
	private String userName;//联系人
	
	private String phone;//联系人电话
	
	//审核开始
	@Column(name="check_flag")
	private String checkFlag;//审核状态（未审核，提交审核,一级审核，二级审核）
	
	@Column(name="check_state")
	private String checkState;
	//审核结果（通过、打回）（这里的审核中指已提交审核，在一级审核后没有用处）（审核中由页面计算显示）
	
	//审核结束
	@ManyToOne
	@JoinColumn(name="create_id")
	private TblUser create;
	
	@Column(name="create_date")
	private String createDate;
	
	@ManyToOne
	@JoinColumn(name="update_id")
	private TblUser update;
	
	@Column(name="update_date")
	private String updateDate;
	
	@Column(name="view_count")
	private int viewCount=0;
	
	@OrderBy("imgStyle")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="room",fetch=FetchType.EAGER)
	private Set<TblRoomImage> imgSet=new HashSet<TblRoomImage>();//楼盘图片集合

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}


	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoomContent() {
		return roomContent;
	}

	public void setRoomContent(String roomContent) {
		this.roomContent = roomContent;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getHallNum() {
		return hallNum;
	}

	public void setHallNum(int hallNum) {
		this.hallNum = hallNum;
	}

	public int getToiletNum() {
		return toiletNum;
	}

	public void setToiletNum(int toiletNum) {
		this.toiletNum = toiletNum;
	}

	public int getKitchenNum() {
		return kitchenNum;
	}

	public void setKitchenNum(int kitchenNum) {
		this.kitchenNum = kitchenNum;
	}

	public TblArea getArea() {
		return area;
	}

	public void setArea(TblArea area) {
		this.area = area;
	}

	public String getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}


	public TblType getType() {
		return type;
	}

	public void setType(TblType type) {
		this.type = type;
	}

	public Set<TblRoomImage> getImgSet() {
		return imgSet;
	}

	public void setImgSet(Set<TblRoomImage> imgSet) {
		this.imgSet = imgSet;
	}

	public String getCompounds() {
		return compounds;
	}

	public void setCompounds(String compounds) {
		this.compounds = compounds;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRentType() {
		return rentType;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCheckFlag() {
		return checkFlag;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public TblUser getCreate() {
		return create;
	}

	public void setCreate(TblUser create) {
		this.create = create;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public TblUser getUpdate() {
		return update;
	}

	public void setUpdate(TblUser update) {
		this.update = update;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
}
