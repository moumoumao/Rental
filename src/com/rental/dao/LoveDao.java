package com.rental.dao;

import java.util.Map;

import com.rental.bean.TblLove;
import com.rental.util.PageBean;

/**
 * 收藏数据接口
 * @author jy
 *t
 */
public interface LoveDao {
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
	public PageBean findByMixAndPage(Map<String, Object> map, int pageSize,
			int pageNo);

}
