package com.web.form;

import java.util.ArrayList;
import java.util.List;

public class FileUploadUim {
	private List list;
	private long totalSize;
	
	public FileUploadUim(){
		this.list 		= new ArrayList();
		this.totalSize 	= 0;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	
}
