package com.wifuns.database.info;

import java.util.List;

import com.wifuns.entity.config.CgFormFieldEntity;

import jodd.db.DbSession;
import jodd.db.connection.ConnectionProvider;
import jodd.db.connection.DriverManagerConnectionProvider;
import jodd.db.oom.DbOomQuery;

public class Dbtest {
	public static void main(String[] args)throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.1.198:3306/jeecg?useUnicode=true&characterEncoding=UTF-8";
		String name = "root";
		String password  =  "1234";
		ConnectionProvider   connectionProvider= new DriverManagerConnectionProvider(driver,url,name,password);
		DbSession session = new DbSession(connectionProvider);
	   
//	    session.beginTransaction();
//	    DbQuery query = new DbQuery(session, "insert into...");
//	    query.executeUpdate();      // 'true' -> query closes after execution
//	    session.commitTransaction();
	    
		DbOomQuery  query2 = new DbOomQuery(session, "select * from cgform_field");
		
	    List<CgFormFieldEntity> girlsAndBoys = query2.list(CgFormFieldEntity.class);
	    for(CgFormFieldEntity cg : girlsAndBoys){
	    	System.out.println(cg.getFieldName());
	    	System.out.println(cg.getLength());
	    
	    }
	    
	      
	}
}
