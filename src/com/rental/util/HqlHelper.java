package com.rental.util;

import java.util.ArrayList;
import java.util.List;

public class HqlHelper {
	private String fromClause = ""; // From子句
	private String joinFetchClause="";
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句
	private List<Object> paramValueList = new ArrayList<Object>(); // （Where子句中所用到的）参数值列表

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 */
	public HqlHelper(Class clazz) {
		fromClause = "FROM " + clazz.getName();
	}

	/**
	 * 生成From子句，并有指定的别名
	 * 
	 * @param clazz
	 * @param alias
	 */
	public HqlHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getName() + " " + alias;
	}
	public HqlHelper addJoin(String condition) {
		// 拼接Where子句
		
		joinFetchClause += " left join fetch " + condition;
		return this;
	}
	/**
	 * 添加Where的一个过滤条件（表达式）与所对应的参数值。（拼接Where子句）
	 * 
	 * @param condition
	 * @param paramValues
	 */
	public HqlHelper addWhereCondition(String condition, Object... paramValues) {
		// 拼接Where子句
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		// 保存参数值到参数值列表中
		if (paramValues != null && paramValues.length > 0) {
			for (Object obj : paramValues) {
				paramValueList.add(obj);
			}
		}

		return this;
	}

	/**
	 * 如果条件成立，就添加一个过滤条件（Where子句中的一个表达式）
	 * 
	 * @param append
	 * @param condition
	 * @param paramValues
	 * @return
	 */
	public HqlHelper addWhereCondition(boolean append, String condition,
			Object... paramValues) {
		if (append) {
			addWhereCondition(condition, paramValues);
		}
		return this;
	}

	/**
	 * 添加Where OR的一个过滤条件（表达式）与所对应的参数值。（拼接Where子句）
	 * 
	 * @param condition
	 * @param paramValues
	 */
	public HqlHelper addWhereConditionOR(String condition,
			Object... paramValues) {
		// 拼接Where子句
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " OR " + condition;
		}

		// 保存参数值到参数值列表中
		if (paramValues != null && paramValues.length > 0) {
			for (Object obj : paramValues) {
				paramValueList.add(obj);
			}
		}

		return this;
	}

	/**
	 * 如果条件成立，就添加一个过滤条件OR（Where子句中的一个表达式）
	 * 
	 * @param append
	 * @param condition
	 * @param paramValues
	 * @return
	 */
	public HqlHelper addWhereConditionOR(boolean append, String condition,
			Object... paramValues) {
		if (append) {
			addWhereConditionOR(condition, paramValues);
		}
		return this;
	}

	/**
	 * 添加一个参与排序的属性，并指定升序或降序。（拼接OrderBy子句）
	 * 
	 * @param propertyName
	 * @param isAsc
	 */
	public HqlHelper addOrderByProperty(String propertyName, boolean isAsc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName
					+ (isAsc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (isAsc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果条件成立，就添加一个排序属性
	 * 
	 * @param append
	 * @param propertyName
	 * @param isAsc
	 * @return
	 */
	public HqlHelper addOrderByProperty(boolean append, String propertyName,
			boolean isAsc) {
		if (append) {
			addOrderByProperty(propertyName, isAsc);
		}
		return this;
	}

	/**
	 * 获取查询数据列表的HQL
	 * 
	 * @return
	 */
	public String getQueryListHql() {
		return fromClause + joinFetchClause+whereClause + orderByClause;
	}

	/**
	 * 获取查询总记录数据HQL（没有OrderBy子句）
	 * 
	 * @return
	 */
	public String getQueryCountHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取参数列表
	 * 
 * @return
	 */
	public List<Object> getParamValueList() {
		return paramValueList;
	}

}
