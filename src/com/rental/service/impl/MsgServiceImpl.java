package com.rental.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.bean.TblMessage;
import com.rental.dao.MessageDao;
import com.rental.service.MsgService;
import com.rental.util.PageBean;
import com.rental.util.StringUtil;
@Service("msgService")
@Transactional
public class MsgServiceImpl implements MsgService {
	@Autowired
	@Qualifier("msgDao")
	private MessageDao msgDao;
	public TblMessage addMessage(TblMessage message) {
		return msgDao.addMessage(message);
	}


	public TblMessage findById(int id) {
		return msgDao.findById(id);
	}

	public PageBean findByMixAndPage(TblMessage msg,String order, int pageSize,
			int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(msg!=null){
			if(!StringUtil.isNullOrEmpty(msg.getMesTitle())){
				map.put("mesTitle", msg.getMesTitle());
			}
			if(!StringUtil.isNullOrEmpty(msg.getMesContent())){
				map.put("mesContent", msg.getMesContent());
			}
			if(msg.getSend()!=null&&msg.getSend().getUserId()!=0){
				map.put("send.userId", msg.getSend().getUserId());
			}
			if(msg.getReceive()!=null&&msg.getReceive().getUserId()!=0){
				map.put("receive.userId", msg.getReceive().getUserId());
			}
			if(msg.getSendShow()!=0){
				map.put("sendShow", msg.getSendShow());
			}
			if(msg.getReceiveShow()!=0){
				map.put("receiveShow", msg.getReceiveShow());
			}
		}
		map.put("order", order);
		return msgDao.findByMixAndPage(map, pageSize, pageNo);
	}

	public void deleteByReceive(TblMessage message) {
		TblMessage obj = msgDao.findById(message.getMesId());
		obj.setReceiveShow(0);
		msgDao.updateMessage(obj);
		//判断发送者是否也已删除，删除则删除数据库
		if(obj.getSendShow()==0){
			msgDao.deleteMessage(obj);
		}
		
	}

	public void deleteBySender(TblMessage message) {
		TblMessage obj = msgDao.findById(message.getMesId());
		obj.setSendShow(0);
		msgDao.updateMessage(obj);
		//判断接收者是否也已删除，删除则删除数据库
		if(obj.getReceiveShow()==0){
			msgDao.deleteMessage(obj);
		}
		
	}

}
