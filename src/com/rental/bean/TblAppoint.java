package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 预约
 * @author jy
 *
 */
@Entity
@Table(name="t_appoint", catalog="rental")
public class TblAppoint {
	@Id
	@GeneratedValue
	@Column(name="appoint_id")
	private int appointId;//主键
	
	@Column(name="appoint_name")
	private String appointerName;//预约者姓名
	
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
	
	@Column(name="num")
	private int num;//预约人数
	
	@Column(name="mobile")
	private String mobile;//预约用户电话
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private TblRoom room;//对应房源信息
	
	@Column(name="appoint_date")
	private String appointDate;//预约时间

	public int getAppointId() {
		return appointId;
	}

	public void setAppointId(int appointId) {
		this.appointId = appointId;
	}

	public String getAppointerName() {
		return appointerName;
	}

	public void setAppointerName(String appointerName) {
		this.appointerName = appointerName;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public TblRoom getRoom() {
		return room;
	}

	public void setRoom(TblRoom room) {
		this.room = room;
	}

	public String getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
	}
	
	
}
