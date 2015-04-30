package com.rental.dao;

import java.util.List;
import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblMessage;;


/**
 * 消息操作接口
 * @author jy
 *
 */
public interface MessageDao {
	/**
	 * 新增消息
	 * @param Message
	 * @return
	 */
	public TblMessage addMessage(TblMessage message);
	/**
	 * 删除消息
	 * @param MessageId
	 * @return
	 */
	public void deleteMessage(TblMessage message);
	/**
	 * 修改消息信息
	 * @param Message
	 * @return
	 */
	public void updateMessage(TblMessage message);
	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	PageBean  findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo)  ;
	/**
	 * 根据Id查找
	 * @return
	 */
	public  TblMessage  findById(int id);
	/**
	 * 查找所有
	 * @return
	 */
	public List<TblMessage> findAll();
}
