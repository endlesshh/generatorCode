package com.wifuns.shop.foundation.dao.impl;

import com.wifuns.shop.core.base.GenericDAO;
import com.wifuns.shop.foundation.dao.I${entityName}DAO;
import com.wifuns.shop.foundation.domain.${entityName};

import org.springframework.stereotype.Repository;

@Repository("${entityName?uncap_first}DAO")
public class ${entityName}DAO extends GenericDAO<${entityName}> implements I${entityName}DAO{
	
}




