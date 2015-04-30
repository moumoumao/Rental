package com.rental.service;


import com.rental.bean.TblLove;
import com.rental.util.PageBean;

/**
 * 收藏数据接口
 * @author jy
 *t
 */
public interface LoveService {
	/**
	 * 新增
	 * @param love
	 * @return
	 */
	public TblLove addLove(TblLove love);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteLove(TblLove love);
	/**
	 * 组合查询
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageBean findByMixAndPage(TblLove love, int pageSize,
			int pageNo);

}
