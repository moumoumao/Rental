package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 功能与URL对应表
 * @author jy
 *
 */
@Entity
@Table(name="t_function",catalog="rental")
public class TblFunction {

	@Id
	@GeneratedValue
	@Column(name="fun_id")
	private int funId;
	
	@Column(name="fun_name")
	private String funName;//功能名称
	
	@Column(name="fater_name")
	private String fatherName;//功能名称
	
	private String url;//功能对应url

	public int getFunId() {
		return funId;
	}

	public void setFunId(int funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
