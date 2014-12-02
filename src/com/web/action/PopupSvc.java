package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * Servlet implementation class PopupSvc
 */
public class PopupSvc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response 	= null;
	private HttpServletRequest 	request 	= null;
	private HttpSession     	session		= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupSvc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doExecute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doExecute(request, response);
	}
	
	private void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[PopupSvc][doExecute][Begin]");
		
		HttpSession     	session			= null;
		String 				act 			= null;

		try{
			
			this.response 	= response;
			this.request	= request;
			this.session 	= request.getSession(false);
			act				= this.request.getParameter("act");
			
			if(act.equals("findData")){
				this.lp_findData();
			}
			

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[PopupSvc][doExecute][End]");
		}
	}
	
	private void lp_findData(){
		System.out.println("[PopupSvc][lp_findData][Begin]");
		
		Document 	document 				= null;
		Element 	esvResponseElement 		= null;
		Element 	bodyElement 			= null;
		Element 	dataListElement 		= null;
		Element 	dataElement		 		= null;
		Element 	codeElement		 		= null;
		Element 	descriptionElement		= null;
		
		try{
			document 				= this.createObjXmlResponse();
			
			esvResponseElement 		= document.getRootElement();
			bodyElement				= esvResponseElement.element("Body");
			
			System.out.println("[PopupSvc][lp_findData] bodyElement :: " + bodyElement.getName());
			
			dataListElement 		= DocumentHelper.createElement("DataList");
			
			for(int i=0;i<3;i++){
				dataElement 			= DocumentHelper.createElement("Data");
				
				codeElement 			= dataElement.addElement("Code");
				codeElement.setText("T");
				
				descriptionElement 		= dataElement.addElement("Description");
				descriptionElement.setText("Tea");
				
				dataListElement.add(dataElement);
			}
			
			bodyElement.add(dataListElement);
			
			this.writeMSG(this.transformXmlObjToString(document));
			
		}catch (Exception e) {
			document 	= this.createObjXmlError(e.getMessage());
			this.writeMSG(document.asXML());
			e.printStackTrace();
		}finally{
			System.out.println("[PopupSvc][lp_findData][End]");
		}
	}
	
	public String transformXmlObjToString(Document 	document){
		System.out.println("[PopupSvc][transformXmlObjToString][Begin]");
		
		String xmlString	= null;
		
		try{
			
			xmlString = document!=null ? document.asXML() : "";
			
			System.out.println("[PopupSvc][transformXmlObjToString] xmlString :: " + xmlString);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[PopupSvc][transformXmlObjToString][End]");
		}
		
		return xmlString;
	}
	
	public Document transformXmlStringToObj(String 	xmlStr){
		System.out.println("[PopupSvc][transformXmlStringToObj][Begin]");
		
		Document document = null;
		
		try{
			System.out.println("[PopupSvc][transformXmlStringToObj] xmlStr :: " + xmlStr);
			
			document = xmlStr!=null && !xmlStr.equals("") ? DocumentHelper.parseText(xmlStr) : null;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[PopupSvc][transformXmlStringToObj][End]");
		}
		
		return document;
	}
	
	public Document createObjXmlResponse(){
		System.out.println("[PopupSvc][createObjXmlResponse][Begin]");
		
		Document 	document 				= null;
		Element 	esvResponseElement 		= null;
		Element 	headerElement 			= null;
		Element 	sourceSystemElement 	= null;
		Element 	resultCodeElement 		= null;
		Element 	bodyElement 			= null;
		
		try{
			document 				= DocumentHelper.createDocument();
			esvResponseElement 		= DocumentHelper.createElement("EsvResponse");
			headerElement 			= DocumentHelper.createElement("Header");
			sourceSystemElement 	= DocumentHelper.createElement("SourceSystem");
			resultCodeElement	 	= DocumentHelper.createElement("ResultCode");
			bodyElement 			= DocumentHelper.createElement("Body");
			
			sourceSystemElement.setText("ESV");
			headerElement.add(sourceSystemElement);
			
			resultCodeElement.setText("OK");
			headerElement.add(resultCodeElement);
			
			esvResponseElement.add(headerElement);
			esvResponseElement.add(bodyElement);
			document.add(esvResponseElement);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[PopupSvc][createObjXmlResponse][End]");
		}
		
		return document;
		
	}
	
	public Document createObjXmlError(String errMsg){
		System.out.println("[PopupSvc][createObjXmlError][Begin]");
		
		Document 	document 				= null;
		Element 	esvResponseElement 		= null;
		Element 	headerElement 			= null;
		Element 	sourceSystemElement 	= null;
		Element 	resultCodeElement 		= null;
		Element 	bodyElement 			= null;
		Element 	errMsgElement 			= null;
		
		try{
			document 				= DocumentHelper.createDocument();
			esvResponseElement 		= DocumentHelper.createElement("EsvResponse");
			headerElement 			= DocumentHelper.createElement("Header");
			sourceSystemElement 	= DocumentHelper.createElement("SourceSystem");
			resultCodeElement	 	= DocumentHelper.createElement("ResultCode");
			bodyElement 			= DocumentHelper.createElement("Body");
			errMsgElement 			= DocumentHelper.createElement("ErrMsg");
			
			sourceSystemElement.setText("ESV");
			headerElement.add(sourceSystemElement);
			
			resultCodeElement.setText("ERROR");
			headerElement.add(resultCodeElement);
			
			errMsgElement.setText(errMsg);
			bodyElement.add(errMsgElement);
			
			esvResponseElement.add(headerElement);
			esvResponseElement.add(bodyElement);
			document.add(esvResponseElement);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[PopupSvc][createObjXmlError][End]");
		}
		
		return document;
		
	}
	
	private void writeMSG(String msg){
		PrintWriter 		print 			= null;
		
		try{
			this.response.setContentType("text/html");
			print = this.response.getWriter();
			print.write(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
