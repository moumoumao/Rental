package com.rental.service;

import java.util.Map;

import com.rental.bean.TblCheckStream;
import com.rental.util.PageBean;

/**
 * 审核流程数据接口
 * @author jy
 *
 */
public interface CheckStreamService {

	/**
	 * 组合、模糊查询
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public 	PageBean  findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo) ;
	/**
	 * 新增（审核员）
	 * @param stream
	 * @return
	 */
	public TblCheckStream addStreamByCheck( TblCheckStream stream);
	/**
	 * 提交审核
	 * @param stream
	 * @return
	 */
	public TblCheckStream addStreamByCreate( TblCheckStream stream);
	/**
	 * 删除
	 * @param stream
	 */
	public void deleteStream( TblCheckStream stream);
}
