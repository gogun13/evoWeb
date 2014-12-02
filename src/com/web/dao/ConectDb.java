package com.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectDb {
	// JDBC driver name and database URL
   static final String 	JDBC_DRIVER 	= "com.mysql.jdbc.Driver";
   static final String 	jdbcutf8 		= "?useUnicode=true&characterEncoding=UTF-8";
   static final String 	DB_NAME 		= "MOTOR";
   static final String 	DB_URL 			= "jdbc:mysql://localhost:3306/" + DB_NAME + jdbcutf8;

   //  Database credentials
   static final String 	USER 			= "root";
   static final String 	PASS 			= "";
   
   private Connection 	conn 			= null;
   private Statement 	stmt 			= null;
   
   public ConectDb(){
	   setConnection();
   }
   
   public ResultSet executeQuery(String sql){
	   System.out.println("[ConectDb][executeQuery][Begin]");
	   
	   ResultSet rs = null;
	   
	   try{
		   rs = this.stmt.executeQuery(sql);
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   System.out.println("[ConectDb][executeQuery][End]");
	   }
	   return rs;
   }
   
   public boolean execute(String sql) throws SQLException{
	   System.out.println("[ConectDb][execute][Begin]");
	   
	   boolean rs = false;
	   
	   try{
		   rs = this.stmt.execute(sql);
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   System.out.println("[ConectDb][execute][End]");
	   }
	   return rs;
   }
   
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	private void setConnection() {
		System.out.println("[ConectDb][setConnection][Begin]");

		try {
			Class.forName(JDBC_DRIVER);

			this.conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			this.stmt = (Statement) this.conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[ConectDb][setConnection][End]");
		}
	}
	   
//	   public static void main(String[] args) {
//	   Connection conn = null;
//	   Statement stmt = null;
//	   try{
//	      //STEP 2: Register JDBC driver
//	      Class.forName(JDBC_DRIVER);
//
//	      //STEP 3: Open a connection
//	      System.out.println("Connecting to database...");
//	      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
//
//	      //STEP 4: Execute a query
//	      System.out.println("Creating statement...");
//	      stmt = (Statement) conn.createStatement();
//	      String sql;
//	      sql = "SELECT id, first, last, age FROM employees";
//	      ResultSet rs = stmt.executeQuery(sql);
//
//	      //STEP 5: Extract data from result set
//	      while(rs.next()){
//	         //Retrieve by column name
//	         int id  = rs.getInt("id");
//	         int age = rs.getInt("age");
//	         String first = rs.getString("first");
//	         String last = rs.getString("last");
//
//	         //Display values
//	         System.out.print("ID: " + id);
//	         System.out.print(", Age: " + age);
//	         System.out.print(", First: " + first);
//	         System.out.println(", Last: " + last);
//	      }
//	      //STEP 6: Clean-up environment
//	      rs.close();
//	      stmt.close();
//	      conn.close();
//	   }catch(SQLException se){
//	      //Handle errors for JDBC
//	      se.printStackTrace();
//	   }catch(Exception e){
//	      //Handle errors for Class.forName
//	      e.printStackTrace();
//	   }finally{
//	      //finally block used to close resources
//	      try{
//	         if(stmt!=null)
//	            stmt.close();
//	      }catch(SQLException se2){
//	      }// nothing we can do
//	      try{
//	         if(conn!=null)
//	            conn.close();
//	      }catch(SQLException se){
//	         se.printStackTrace();
//	      }//end finally try
//	   }//end try
//	   System.out.println("Goodbye!");
//	}//end main
}
