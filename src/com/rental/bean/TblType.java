package com.rental.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 房源类型（公寓，平房）
 * @author jy
 *
 */
@Entity
@Table(name="t_type",catalog="rental")
public class TblType {
	@Id
	@GeneratedValue
	@Column(name="type_id")
	private int typeId;//主键
	
	@Column(name="type_name")
	private String typeName;//类型名
	
	@Column(name="type_desc")
	private String describe;//类型描述
	
	@ManyToOne
	@JoinColumn(name="create_id")
	private TblUser create;//创建者
	
	@Column(name="create_date")
	private String createDate;//创建时间
	
//	@OrderBy("roomId")
//	@OneToMany(cascade=CascadeType.MERGE, mappedBy="area",fetch=FetchType.EAGER)
//	private Set<TblRoom> roomSet=new HashSet<TblRoom>();//房源集合

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
