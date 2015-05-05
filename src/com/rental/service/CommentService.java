package com.rental.service;

import com.rental.util.PageBean;
import com.rental.bean.TblComment;;


/**
 * 评论业务操作接口
 * @author jy
 *
 */
public interface CommentService {
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
	public void deleteComment(TblComment comment);
	/**
	 * 组合模糊查询 并分页
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	PageBean  findByMixAndPage(TblComment comment,
			int pageSize, int pageNo)  ;
}
