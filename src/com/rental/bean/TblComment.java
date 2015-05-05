package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评论
 * @author jy
 *
 */
@Entity
@Table(name="t_comment ",catalog="rental")
public class TblComment {


	@Id
	@GeneratedValue
	@Column(name="com_id")
	private int comId;//主键
	
	@ManyToOne
	@JoinColumn(name="userId")
	private TblUser user;//评论者
	
	
	
	@Column(name="com_content")
	private String comContent;//内容
	
	@Column(name="create_date")
	private String createDate;//创建时间
	

	@ManyToOne
	@JoinColumn(name="room_id")
	private TblRoom room;//对应房源Id


	public int getComId() {
		return comId;
	}


	public void setComId(int comId) {
		this.comId = comId;
	}


	public TblUser getUser() {
		return user;
	}


	public void setUser(TblUser user) {
		this.user = user;
	}


	public String getComContent() {
		return comContent;
	}


	public void setComContent(String comContent) {
		this.comContent = comContent;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public TblRoom getRoom() {
		return room;
	}


	public void setRoom(TblRoom room) {
		this.room = room;
	}
	

}
