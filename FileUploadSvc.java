package com.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.web.form.FileUploadUim;
import com.web.mod.ExcelColMod;
import com.web.mod.FileUploadMod;
import com.web.util.ExcelUtil;
import com.web.util.FormatUtil;

/**
 * Servlet implementation class FileUploadSvc
 */
public class FileUploadSvc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest 	request 	= null;
	private HttpSession     	session		= null;
	private FormatUtil			formatUtil	= null;
	
    public FileUploadSvc() {
        super();pppp
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doExecute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doExecute(request, response);
	}
	
	private void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[FileUploadSvc][doExecute][Begin]");
		
		FileUploadUim		fileUploadUim	= null;
		String 				act 			= null;

		try{		
			this.session 		= request.getSession(false);
			this.request		= request;
			act					= this.request.getParameter("act");
			this.formatUtil		= new FormatUtil(request, response);
			
			if(this.session.getAttribute("uim")==null){
				fileUploadUim = new FileUploadUim();
				this.session.setAttribute("uim", fileUploadUim);
			}
			
			if(act.equalsIgnoreCase("upFile")){
				this.lp_setFileToTemp();
			}else if(act.equalsIgnoreCase("writeFile")){
				this.lp_writingFile();
			}else if(act.equalsIgnoreCase("readExcelData")){
				this.lp_readExcelData();
			}
			

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[FileUploadSvc][doExecute][End]");
		}
	}
	
	private void lp_setFileToTemp(){
		System.out.println("[FileUploadSvc][lp_setFileToTemp][Begin]");
		
		boolean 			isMultipart 	= ServletFileUpload.isMultipartContent(this.request);
		FileItemFactory 	factory 		= null;
		ServletFileUpload 	upload 			= null;
		List 				items			= null;
		Iterator 			iterator 		= null;
		String 				fileName 		= null;
		long				size			= 0;
		long 				curr 			= System.currentTimeMillis();
		FileUploadMod		fileUploadMod	= null;
		long 				totalSize		= 0;
		long 				limitSize		= 20480000;//2 MB
		FileUploadUim		fileUploadUim	= null;
		
		try{
			System.out.println("[FileUploadSvc][doExecute] isMultipart :: " + isMultipart);
			
			if (isMultipart) {
				 factory 			= new DiskFileItemFactory();
				 upload 			= new ServletFileUpload(factory);
//				 root 				= "D:/uploads";
				 fileUploadUim		= (FileUploadUim) this.session.getAttribute("uim");
				 
				// Parse the request
			    items 		= upload.parseRequest(this.request);
			    iterator 	= items.iterator();
			    
			    while (iterator.hasNext()) {
			    	FileItem item = (FileItem) iterator.next();
			    	if (!item.isFormField()) {
			    		fileUploadMod 		= new FileUploadMod();
//			    		fileName 	= item.getName();  
			    		fileName 			= new File(item.getName()).getName(); 
//			    		root 		= getServletContext().getRealPath("/");
//			    		path 				= new File(root);
			    		size				= item.getSize();
			    		totalSize			= size + fileUploadUim.getTotalSize();
			    		
			    		System.out.println("FileUploadSvc][doExecute] fileName :: " + fileName);
			    		System.out.println("FileUploadSvc][doExecute] size :: " + size);
			    		System.out.println("FileUploadSvc][doExecute] totalSize :: " + totalSize);
//			    		System.out.println("FileUploadSvc][doExecute] root :: " + root);
			    		System.out.println("FileUploadSvc][doExecute] curr :: " + curr);
//			    		System.out.println("FileUploadSvc][doExecute] path.exists() :: " + path.exists());
			    		
			    		fileUploadMod.setFileName(fileName);
			    		fileUploadMod.setTimeStamp(String.valueOf(curr));
			    		fileUploadMod.setSize(size);
			    		fileUploadMod.setItem(item);
			    		
			    		fileUploadUim.getList().add(fileUploadMod);
			    		
//					    if (!path.exists()) {
//					    	path.mkdirs();
//			            }
//			  
////	                    File uploadedFile = new File(path + "/" + fileName);
//	                    File uploadedFile = new File(path + "/d.pdf");
//	                    
//	                    System.out.println("FileUploadSvc][doExecute] uploadedFile.getAbsolutePath() :: " + uploadedFile.getAbsolutePath());
//	                    item.write(uploadedFile);
			         }
			    }
			    
			    fileUploadUim.setTotalSize(totalSize);
			    this.session.setAttribute("uim", fileUploadUim);
			}
			
			this.formatUtil.writeMSG("OK");
		}catch(Exception e){
			
		}finally{
			System.out.println("[FileUploadSvc][lp_setFileToTemp][End]");
		}
	}
	
	private void lp_writingFile(){
		System.out.println("[FileUploadSvc][lp_writingFile][Begin]");
		
		FileUploadUim		fileUploadUim	= null;
		String 				root 			= null;
		File 				path 			= null;
		List				list			= null;
		FileUploadMod		fileUploadMod	= null;
		File 				uploadedFile 	= null;
		FileItem 			item 			= null;
		String 				fileName 		= null;
		
		try{
			root 				= "D:/uploads";
			path 				= new File(root);
			fileUploadUim		= (FileUploadUim) this.session.getAttribute("uim");
			list				= fileUploadUim.getList();
			
			System.out.println("FileUploadSvc][lp_writingFile] path.exists() :: " + path.exists());
			
			if (!path.exists()) {
		    	path.mkdirs();
            }
			
			for(int i=0;i<list.size();i++){
				fileUploadMod 	= (FileUploadMod) list.get(i);
				item			= fileUploadMod.getItem();
				fileName		= fileUploadMod.getTimeStamp() + ".pdf";
				
				uploadedFile = new File(path + "/" + fileName);
                
                item.write(uploadedFile);
			}
			
			this.formatUtil.writeMSG("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("[FileUploadSvc][lp_writingFile][End]");
		}
	}
	
	private void lp_readExcelData(){
		System.out.println("[FileUploadSvc][lp_readExcelData][Begin]");
		
		boolean 			isMultipart 	= ServletFileUpload.isMultipartContent(this.request);
		FileItemFactory 	factory 		= null;
		ServletFileUpload 	upload 			= null;
		List 				items			= null;
		Iterator 			iterator 		= null;
		String 				fileName 		= null;
		File 				uploadedFile 	= null;
		long 				curr 			= System.currentTimeMillis();
		String[]			extentArr		= null;
		String				extent			= null;
		Workbook 			wb				= null;
		int 				numberOfSheets, startRow, endRow, startCell, endCell;
		Sheet 				sheet			= null;
		String 				sheetName 		= "";
		Row[]   			rowArray  		= null;
		ExcelColMod			mod				= null;
		String				colA			= null;
		String				colB			= null;
		String				colC			= null;
		String				colD			= null;
		boolean				del				= false;
		
		try{
			if (isMultipart) {
				factory 		= new DiskFileItemFactory();
				upload 			= new ServletFileUpload(factory);
			    items 			= upload.parseRequest(this.request);
			    iterator 		= items.iterator();
			    
			    while (iterator.hasNext()) {
			    	FileItem item = (FileItem) iterator.next();
			    	if (!item.isFormField()) {
			    		fileName 			= new File(item.getName()).getName();
			    		extentArr			= fileName.split("\\.");
			    		extent				= extentArr[(extentArr.length - 1)];
			    		fileName			= String.valueOf(curr) + "." + extent;
			    		
			    		System.out.println("FileUploadSvc][lp_readExcelData] fileName :: " + fileName);
	
			    		uploadedFile = new File(fileName);
			    		item.write(uploadedFile);
			    		
			    		wb             = ExcelUtil.getWorkbook(uploadedFile);
			    		numberOfSheets = wb.getNumberOfSheets();
			    		
						for (int i = 0; i < numberOfSheets; i++) {
							sheetName = wb.getSheetName(i);
							sheet     = wb.getSheetAt(i);
							startRow  = sheet.getFirstRowNum();
							endRow    = sheet.getPhysicalNumberOfRows();
							rowArray  = ExcelUtil.getAllRows(sheet);
							
							System.out.println("FileUploadSvc][lp_readExcelData] sheetName :: " + sheetName);
							
							for(int j=0;j<rowArray.length;j++){
								mod 	= new ExcelColMod(rowArray[j]);
								colA	= mod.getColA().getValue();
								colB	= mod.getColB().getValue();
								colC	= mod.getColC().getValue();
								colD	= mod.getColD().getValue();
								
								System.out.println("FileUploadSvc][lp_readExcelData] col :: " + colA + " " + colB + " " + colC + " " + colD);
								
							}
						}
			    		
						del = uploadedFile.delete();
						System.out.println("FileUploadSvc][lp_readExcelData] del :: " + del);
			    		
			         }
			    }
			}
			
			this.formatUtil.writeMSG("OK");

		}catch(Exception e){
			e.printStackTrace();
			this.formatUtil.writeMSG(e.getMessage());
		}finally{
			System.out.println("[FileUploadSvc][lp_readExcelData][End]");
		}
	}

}
