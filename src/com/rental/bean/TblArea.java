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
 * 区域
 * @author jy
 *
 */
@Entity
@Table(name="t_area",catalog="rental")
public class TblArea {


	@Id
	@GeneratedValue
	@Column(name="area_id")
	private int areaId;//主键
	
	@Column(name="area_name")
	private String areaName;//区域名
	
	@Column(name="area_desc")
	private String describe;//区域描述
	
	@ManyToOne
	@JoinColumn(name="create_id")
	private TblUser create;//创建者
	
	@Column(name="create_date")
	private String createDate;//创建时间
	
//	@OrderBy("roomId")
//	@OneToMany(cascade=CascadeType.MERGE, mappedBy="area",fetch=FetchType.EAGER)
//	private Set<TblRoom> roomSet=new HashSet<TblRoom>();//房源集合

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

//	public Set<TblRoom> getRoomSet() {
//		return roomSet;
//	}
//
//	public void setRoomSet(Set<TblRoom> roomSet) {
//		this.roomSet = roomSet;
//	}

	
	
	

}
