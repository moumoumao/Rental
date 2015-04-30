package com.rental.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 角色
 * @author jy
 *
 */
@Entity
@Table(name="t_role",catalog="rental")
public class TblRole {
	@Id
	@GeneratedValue
	@Column(name="role_id")
	private int roleId;//用户编号
	
	@Column(name="role_name")
	private String roleName;//角色名称
	
	@Column(name="role_content")
	private String roleContent;//角色详细介绍
	
	@OrderBy("powerId")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="role",fetch=FetchType.EAGER)
	private Set<TblPower>  powerSet = new HashSet<TblPower>();
	public TblRole() {
		// TODO Auto-generated constructor stub
	}
	public TblRole(int roleId) {
		this.roleId =roleId ;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleContent() {
		return roleContent;
	}

	public void setRoleContent(String roleContent) {
		this.roleContent = roleContent;
	}

	public Set<TblPower> getPowerSet() {
		return powerSet;
	}

	public void setPowerSet(Set<TblPower> powerSet) {
		this.powerSet = powerSet;
	}
	
}
