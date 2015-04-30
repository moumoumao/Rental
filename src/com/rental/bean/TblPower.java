package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 权限
 * @author jy
 *
 */
@Entity
@Table(name="t_power",catalog="rental")
public class TblPower {
	@Id
	@GeneratedValue
	@Column(name="power_id")
	private int powerId;
	
	@ManyToOne
	@JoinColumn(name="fun_id")
	private TblFunction fun;//功能名称
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private TblRole role;//角色

	public int getPowerId() {
		return powerId;
	}

	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}

	public TblFunction getFun() {
		return fun;
	}

	public void setFun(TblFunction fun) {
		this.fun = fun;
	}

	@JSON(serialize=false)
	public TblRole getRole() {
		return role;
	}

	public void setRole(TblRole role) {
		this.role = role;
	}
	
}
