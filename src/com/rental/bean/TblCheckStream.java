package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 审核流
 * @author jy
 *
 */
@Entity
@Table(name="t_check_stream",catalog="rental")
public class TblCheckStream {
	@Id
	@GeneratedValue
	@Column(name="check_id")
	private int checkId;//主键
	
	@ManyToOne
	@JoinColumn(name="create_id")
	private TblUser create;//审核人
	
	@Column(name="check_flag")
	private String checkFlag;//审核者状态（提交者，一级审核，二级审核）
	
	@Column(name="create_date")
	private String createDate;
	
	@Column(name="check_state")
	private String checkState;//审核状态 ( 通过、打回)
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private TblRoom room;//审核房源
	
	@Column(name="check_reason")
	private String checkReason;//审核意见

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
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


	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public TblRoom getRoom() {
		return room;
	}

	public void setRoom(TblRoom room) {
		this.room = room;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	
}
