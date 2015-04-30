package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 房源图片
 * @author jy
 *
 */
@Entity
@Table(name="t_room_img",catalog="rental")
public class TblRoomImage {
	@Id
	@GeneratedValue
	@Column(name="img_id")
	private int ImgId;//主键
 
	@Column(name="img_path")
	private String imgPath;//图片地址
	
	@Column(name="img_style")
	private String imgStyle;//图片类型
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private TblRoom room;//对应房源Id
	
	@ManyToOne
	@JoinColumn(name="create_id")
	private TblUser create;
	
	@Column(name="create_date")
	private String createDate;

	public int getImgId() {
		return ImgId;
	}

	public void setImgId(int imgId) {
		ImgId = imgId;
	}


	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgStyle() {
		return imgStyle;
	}

	public void setImgStyle(String imgStyle) {
		this.imgStyle = imgStyle;
	}

	public TblRoom getRoom() {
		return room;
	}

	public void setRoom(TblRoom room) {
		this.room = room;
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

	
	
	
}
