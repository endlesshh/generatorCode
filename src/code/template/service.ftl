package com.wifuns.shop.foundation.service;

import com.wifuns.shop.core.query.support.IPageList;
import com.wifuns.shop.core.query.support.IQueryObject;
import com.wifuns.shop.foundation.domain. ${entityName};

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName. I${entityName}Service
 * @Description. ${ftl_description}
 * @author ${author}
 * @date. ${ftl_create_time}
 * @version V1.0
 */

public interface I${entityName}Service {
	
	/**
	 * 
	 * @Title. save
	 * @Description. 添加
	 * @param ${entityName} param 
	 * @return boolean 
	 */
	public boolean save(${entityName} param);

	/**
	 * 
	 * @Title. delete
	 * @Description. 根据Id删除
	 * @param Long param 
	 * @return boolean
	 */
	public boolean delete(Long paramLong);

	/**
	 * 
	 * @Title. update
	 * @Description. 修改信息
	 * @param ${entityName} param 
	 * @return boolean
	 */
	public boolean update(${entityName} param);
	
	/**
	 * 
	 * @Title. list
	 * @Description. 获取列表
	 * @param IQueryObject param
	 * @return IPageList
	 * @exception TODO
	 */
	public IPageList list(IQueryObject param);

	/**
	 * 
	 * @Title. getObjById
	 * @Description. 根据Id获取单条信息
	 * @param Long paramLong
	 * @return ${entityName}
	 */
	public ${entityName} getObjById(Long param);

	/**
	 * 
	 * @Title. getObjByProperty
	 * @Description. 根据条件获取单条信息
	 * @param String param1
	 * @param String param2
	 * @return ${entityName}
	 */
	public ${entityName} getObjByProperty(String param1, String param2);

	/**
	 * 
	 * @Title. query
	 * @Description. 获取列表
	 * @param String param
	 * @param Map paramMap
	 * @param int paramInt1
	 * @param int paramInt2
	 * @return List<${entityName}>
	 */
	public List<${entityName}> query(String paramString, Map paramMap,
			int paramInt1, int paramInt2);
}

