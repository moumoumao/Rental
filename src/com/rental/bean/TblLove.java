package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 收藏
 * @author jy
 *
 */
@Entity
@Table(name="t_love ",catalog="rental")
public class TblLove {


	@Id
	@GeneratedValue
	@Column(name="love_id")
	private int loveId;//主键
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private TblUser user;//收藏者
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private TblRoom room;//收藏房源
	
	
	@Column(name="create_date")
	private String createDate;//收藏时间






	public int getLoveId() {
		return loveId;
	}


	public void setLoveId(int loveId) {
		this.loveId = loveId;
	}


	public TblUser getUser() {
		return user;
	}


	public void setUser(TblUser user) {
		this.user = user;
	}


	public TblRoom getRoom() {
		return room;
	}


	public void setRoom(TblRoom room) {
		this.room = room;
	}


	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
