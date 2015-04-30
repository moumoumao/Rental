package com.rental.service;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblMessage;;


/**
 * 消息业务操作接口
 * @author jy
 *
 */
public interface MsgService {
	/**
	 * 新增消息
	 * @param Message
	 * @return
	 */
	public TblMessage addMessage(TblMessage message);
	/**
	 * 发送方删除消息
	 * @param MessageId
	 * @return
	 */
	public void deleteBySender(TblMessage message);
	/**
	 * 接收方删除消息
	 * @param MessageId
	 * @return
	 */
	public void deleteByReceive(TblMessage message);
	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param data (创建日期 asc升序，desc降序)
	 * @param pageNo
	 * @return
	 */
	PageBean  findByMixAndPage(TblMessage msg,String order,
			int pageSize, int pageNo)  ;
	/**
	 * 根据Id查找
	 * @return
	 */
	public  TblMessage  findById(int id);
}
