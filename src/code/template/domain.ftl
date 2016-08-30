package com.wifuns.shop.foundation.domain;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wifuns.shop.core.constant.Globals;
import com.wifuns.shop.core.domain.IdEntity;
/**
 * 
 * @ClassName. ${entityName}
 * @Description. ${ftl_description}
 * @author ${author}
 * @date. ${ftl_create_time}
 * @version V1.0
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = Globals.DEFAULT_TABLE_SUFFIX+"${tableName}")
public class ${entityName} extends IdEntity{

<#list originalColumns as po>
	<#if po.fieldName != wifuns_table_id>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#if> 
</#list> 

<#list originalColumns as po>
	<#if po.fieldName != wifuns_table_id >
	/**
	 *方法: 取得${po.fieldType}
	 *@return: ${po.fieldType}  ${po.filedComment}
	 */
	
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.fieldType}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#if> 
</#list>
}
