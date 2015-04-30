package com.rental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 站内信
 * @author jy
 *
 */
@Entity
@Table(name="t_messages ",catalog="rental")
public class TblMessage {


	@Id
	@GeneratedValue
	@Column(name="mes_id")
	private int mesId;//主键
	
	@ManyToOne
	@JoinColumn(name="sendId")
	private TblUser send;//发送者
	
	@ManyToOne
	@JoinColumn(name="receive_id")
	private TblUser receive;//接收者
	
	@Column(name="mes_title")
	private String mesTitle;//标题
	
	
	@Column(name="mes_content")
	private String mesContent;//内容
	
	@Column(name="create_date")
	private String createDate;//创建时间
	
	@Column(name="send_show")
	private int sendShow=1;//发送方是否显示
	
	@Column(name="receive_show")
	private int receiveShow=1;//接收方是否显示

	public int getMesId() {
		return mesId;
	}

	public void setMesId(int mesId) {
		this.mesId = mesId;
	}

	public TblUser getSend() {
		return send;
	}

	public void setSend(TblUser send) {
		this.send = send;
	}

	public TblUser getReceive() {
		return receive;
	}

	public void setReceive(TblUser receive) {
		this.receive = receive;
	}

	public String getMesTitle() {
		return mesTitle;
	}

	public void setMesTitle(String mesTitle) {
		this.mesTitle = mesTitle;
	}

	public String getMesContent() {
		return mesContent;
	}

	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getSendShow() {
		return sendShow;
	}

	public void setSendShow(int sendShow) {
		this.sendShow = sendShow;
	}

	public int getReceiveShow() {
		return receiveShow;
	}

	public void setReceiveShow(int receiveShow) {
		this.receiveShow = receiveShow;
	}

	
}
