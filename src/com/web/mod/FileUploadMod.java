package com.web.mod;

import org.apache.commons.fileupload.FileItem;

public class FileUploadMod {
	private FileItem 	item;
	private String		fileName;
	private long		size;
	private String		timeStamp;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public FileItem getItem() {
		return item;
	}
	public void setItem(FileItem item) {
		this.item = item;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	
	
}
