package com.wifuns.shop.foundation.domain.query;

import com.wifuns.shop.core.query.QueryObject;

import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName. ${entityName}QueryObject
 * @Description.  ${ftl_description}
 * @author ${author}
 * @date. ${ftl_create_time}
 * @version V1.0
 */
public class ${entityName}QueryObject extends QueryObject {
	public ${entityName}QueryObject(String currentPage, ModelAndView mv,
			String orderBy, String orderType) {
		super(currentPage, mv, orderBy, orderType);
	}

	public ${entityName}QueryObject() {
	}
}

