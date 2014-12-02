package com.web.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.web.util.FormatUtil;

/**
 * Servlet implementation class ReadXmlTest
 */
public class ReadXmlTestSvc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest 	request 	= null;
	private FormatUtil			formatUtil	= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadXmlTestSvc() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doExecute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doExecute(request, response);
	}
	
	private void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[ReadXmlTest][doExecute][Begin]");
		
		String 				act 			= null;

		try{
			this.request		= request;
			act					= this.request.getParameter("act");
			this.formatUtil		= new FormatUtil(request, response);
			
			if(act.equalsIgnoreCase("onload")){
				this.lp_returnXml();
			}else if(act.equalsIgnoreCase("json")){
				this.lp_returnJson();
			}else if(act.equalsIgnoreCase("readJson")){
				this.lp_readJson();
			}
			

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[ReadXmlTest][doExecute][End]");
		}
	}
	
	private void lp_returnXml(){
		System.out.println("[ReadXmlTest][lp_returnXml][Begin]");
		
		FileInputStream 	fstream 		= null;
		DataInputStream 	instream 		= null;
		BufferedReader	 	bf 				= null;
		String 				line 			= null;
		StringBuffer		sb				= new StringBuffer();
		
		try{
			fstream 	= new FileInputStream("D:\\uploads\\test.xml");
			instream 	= new DataInputStream(fstream);
		    bf 			= new BufferedReader(new InputStreamReader(instream));
		    
		    while ((line = bf.readLine()) != null) {
		    	sb.append(line);
		    }

		    instream.close();
		    
		    this.formatUtil.writeMSG(sb.toString());
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[ReadXmlTest][lp_returnXml][End]");
		}
	}
	
	private void lp_returnJson(){
		System.out.println("[ReadXmlTest][lp_returnJson][Begin]");
		
		JSONObject 	obj 	= null;
		JSONObject 	obj3 	= null;
		JSONArray 	list 	= null;
		
		try{
			obj3 	= new JSONObject();
			list 	= new JSONArray();
			
			obj 	= new JSONObject();
			obj.put("name", "foo");
			obj.put("num", new Integer(100));
			obj.put("balance", new Double(1000.21));
			obj.put("is_vip", new Boolean(true));
			
			list.add(obj);
			
			obj 	= new JSONObject();
			obj.put("name", "Pound");
			obj.put("num", new Integer(99));
			obj.put("balance", new Double(1200.50));
			obj.put("is_vip", new Boolean(true));
			
			list.add(obj);
			
			obj3.put("company", "Summit computer");
			obj3.put("messages", list);

		    this.formatUtil.writeMSG(obj3.toString());
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[ReadXmlTest][lp_returnJson][End]");
		}
	}
	
	private void lp_readJson(){
		System.out.println("[ReadXmlTest][lp_readJson][Begin]");
		
		JSONParser 		parser 			= null;
		Object 			obj 			= null;
		JSONObject 		jsonObject 		= null;
		JSONArray		list			= null;
		JSONObject		jsonObj			= null;
		Double			balance			= null;
		String			strRequest		= null;
		String			company			= null;
		
		try{
			strRequest		=  this.request.getParameter("strRequest");
			parser 			= new JSONParser();
//			obj 			= parser.parse(new FileReader("D:\\uploads\\json.txt"));
			obj 			= parser.parse(strRequest);
			jsonObject 		= (JSONObject) obj;
			company			= (String) jsonObject.get("company");
			list			= (JSONArray) jsonObject.get("messages");
			
			System.out.println("[ReadXmlTest][lp_readJson] strRequest 	:: " + strRequest);
			System.out.println("[ReadXmlTest][lp_readJson] company 		:: " + company);
			
			for(int i=0;i<list.size();i++){
				jsonObj = (JSONObject) list.get(i);
				
				balance = (Double) jsonObj.get("balance");
				
				System.out.println("[ReadXmlTest][lp_readJson] balance :: " + balance);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[ReadXmlTest][lp_readJson][End]");
		}
	}

}












