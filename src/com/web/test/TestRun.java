package com.web.test;

import java.sql.ResultSet;

import com.web.dao.ConectDb;

public class TestRun {

	public static void main(String[] args) {
//		testSelect();
//		testInsert();
//		testDelete();
		testUpdate();

	}
	
	private static void testSelect(){
		System.out.println("[TestRun][test][Begin]");
		ConectDb 	conectDb 		= null;
		String 		sql			 	= null;
		ResultSet 	rs 				= null;
		int 		id  			= 0;
		int 		age 			= 0;
        String 		first 			= null;
        String 		last 			= null;
		
		try{
			conectDb 	= new ConectDb();
			sql 		= "SELECT id, first, last, age FROM employees";
		    rs 			= conectDb.executeQuery(sql);
		    
		    while(rs.next()){
		         id  	= rs.getInt("id");
		         age 	= rs.getInt("age");
		         first 	= rs.getString("first");
		         last 	= rs.getString("last");

		         System.out.println("[TestRun][testSelect] ID		:: " + id);
		         System.out.println("[TestRun][testSelect] Age	:: " + age);
		         System.out.println("[TestRun][testSelect] First	:: " + first);
		         System.out.println("[TestRun][testSelect] Last	:: " + last);
		      }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[TestRun][test][End]");
		}
	}
	
	private static void testInsert(){
		System.out.println("[TestRun][testInsert][Begin]");
		ConectDb 	conectDb 		= null;
		String 		sql			 	= null;
		boolean 	rs 				= false;
		
		try{
			conectDb 	= new ConectDb();
			sql 		= "insert into employees values ( 0,'สุกานดา' ,'จิตทรัพย์' ,25)";
		    rs 			= conectDb.execute(sql);
		    
		    System.out.println("[TestRun][testInsert] rs	:: " + rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[TestRun][testInsert][End]");
		}
	}
	
	private static void testDelete(){
		System.out.println("[TestRun][testDelete][Begin]");
		ConectDb 	conectDb 		= null;
		String 		sql			 	= null;
		boolean 	rs 				= false;
		
		try{
			conectDb 	= new ConectDb();
			sql 		= "delete from employees where id in (6, 7)";
		    rs 			= conectDb.execute(sql);
		    
		    System.out.println("[TestRun][testDelete] rs	:: " + rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[TestRun][testDelete][End]");
		}
	}
	
	private static void testUpdate(){
		System.out.println("[TestRun][testUpdate][Begin]");
		ConectDb 	conectDb 		= null;
		String 		sql			 	= null;
		boolean 	rs 				= false;
		
		try{
			conectDb 	= new ConectDb();
			sql 		= "update employees set age = 26 where id = 8";
		    rs 			= conectDb.execute(sql);
		    
		    System.out.println("[TestRun][testUpdate] rs	:: " + rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[TestRun][testUpdate][End]");
		}
	}

}
