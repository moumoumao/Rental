package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户类
 * @author jy
 *
 */
@Entity
@Table(name="t_user",catalog="rental")
public class TblUser {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;//用户编号
	
	@Column(name="login_name")
	private String loginName;//登录名
	
	@Column(name="user_name")
	private String userName;//用户昵称
	
	@Column(name="user_sex")
	private String userSex;//用户性别
	
	@Column(name="phone")
	private String phone;//手机
	
	@Column(name="email")
	private String email;//手机
	
	@Column(name="user_pwd")
	private String userPwd;//用户密码
	
	@Column(name="create_date")
	private String createDate;//注册时间
	
	@Column(name="last_date")
	private String lastDate;//最后登录时间
	
	@Column(name="login_flag")
	private int loginFlag=1;//是否有登录的权限(1为有，2为没有)
	
	@Column(name="head_img")
	private String headImg;//头像地址
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private TblRole role ;//角色
	
	private String birth;//出身年月
	
	@Column(name="check_flag")
	private int check_flag=0;//几级审核(0没有审核权限 ，1 为一级审核， 2为二级审核  )

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public int getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(int loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public TblRole getRole() {
		return role;
	}

	public void setRole(TblRole role) {
		this.role = role;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCheck_flag() {
		return check_flag;
	}

	public void setCheck_flag(int checkFlag) {
		check_flag = checkFlag;
	}
	
	
}
