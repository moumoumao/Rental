package com.rental.dao;

import java.util.Map;

import com.rental.util.PageBean;
import com.rental.bean.TblComment;;


/**
 * 评论操作接口
 * @author jy
 *
 */
public interface CommentDao {
	/**
	 * 新增评论
	 * @param comment
	 * @return
	 */
	public TblComment addcomment(TblComment comment);
	/**
	 * 删除评论
	 * @param commentId
	 * @return
	 */
	public void deletecomment(TblComment comment);
	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	PageBean  findByMixAndPage(Map<String, Object> map,
			int pageSize, int pageNo)  ;
}
