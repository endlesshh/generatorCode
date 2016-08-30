package com.wifuns.shop.foundation.service.impl;

import com.wifuns.shop.core.dao.IGenericDAO;
import com.wifuns.shop.core.query.GenericPageList;
import com.wifuns.shop.core.query.PageObject;
import com.wifuns.shop.core.query.support.IPageList;
import com.wifuns.shop.core.query.support.IQueryObject;
import com.wifuns.shop.foundation.domain.${entityName};
import com.wifuns.shop.foundation.service.I${entityName}Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @ClassName. ${entityName}Service
 * @Description. ${ftl_description}
 * @author ${author}
 * @date. ${ftl_create_time}
 * @version V1.0
 */
@Service
@Transactional
public class ${entityName}ServiceImpl implements I${entityName}Service {

	@Resource(name = "${entityName?uncap_first}DAO")
	private IGenericDAO<${entityName}> ${entityName?uncap_first}DAO;

	/**
	 * 
	 * @Title. delete
	 * @Description. 根据id删除
	 * @param Long id
	 * @return boolean
	 */
	public boolean delete(Long id) {
		try {
			this.${entityName?uncap_first}DAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Title. getObjById
	 * @Description. 根据Id获取单条记录
	 * @param Long id
	 * @return ${entityName}
	 */
	public ${entityName} getObjById(Long id) {
		return (${entityName}) this.${entityName?uncap_first}DAO.get(id);
	}

	/**
	 * 
	 * @Title. save
	 * @Description. 添加
	 * @param ${entityName}  param
	 * @return boolean
	 */
	public boolean save(${entityName} param) {
		try {
			this.${entityName?uncap_first}DAO.save(param);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Title. update
	 * @Description. 修改信息
	 * @param  param
	 * @return boolean
	 */
	public boolean update(${entityName} param) {
		try {
			this.${entityName?uncap_first}DAO.update(param);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Title. query
	 * @Description. 获取列表
	 * @param String query sql片段
	 * @param Map params sql中的参数
	 * @param int begin  开始位置
	 * @param int max 获取条数
	 * @return list<${entityName}>
	 */
	public List<${entityName}> query(String query, Map params, int begin, int max) {
		return this.${entityName?uncap_first}DAO.query(query, params, begin, max);
	}

	/**
	 * 
	 * @Title. list
	 * @Description. 获取列表
	 * @param IQueryObject properties
	 * @return IPageList
	 */
	public IPageList list(IQueryObject properties) {
		if (properties == null) {
			return null;
		}
		String query = properties.getQuery();
		Map<?, ?> params = properties.getParameters();
		GenericPageList pList = new GenericPageList(${entityName}.class, query,
				params, this.${entityName?uncap_first}DAO);
		PageObject pageObj = properties.getPageObj();
		if (pageObj != null){
			pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj
					.getCurrentPage().intValue(),
					pageObj.getPageSize() == null ? 0 : pageObj
							.getPageSize().intValue());
		}
		return pList;
	}

	/**
	 * 
	 * @Title. getObjByProperty
	 * @Description. 根据条件查找 
	 * @param String propertyName 查询字段
	 * @param String value  字段值
	 * @return
	 */
	public ${entityName}  getObjByProperty(String propertyName, String value) {
		return (${entityName}) this.${entityName?uncap_first}DAO.getBy(propertyName, value);
	}
}

