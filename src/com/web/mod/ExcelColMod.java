package com.web.mod;

import org.apache.poi.ss.usermodel.Row;

import com.web.util.ExcelField;


public class ExcelColMod {
	
	private ExcelField colA;
	private ExcelField colB;
	private ExcelField colC;
	private ExcelField colD;
	
	public ExcelColMod(Row row){
		this.colA			= new ExcelField(row, ".*", 0);
		this.colB			= new ExcelField(row, ".*", 1);
		this.colC			= new ExcelField(row, ".*", 2);
		this.colD			= new ExcelField(row, ".*", 3);
	}

	public ExcelField getColA() {
		return colA;
	}

	public void setColA(ExcelField colA) {
		this.colA = colA;
	}

	public ExcelField getColB() {
		return colB;
	}

	public void setColB(ExcelField colB) {
		this.colB = colB;
	}

	public ExcelField getColC() {
		return colC;
	}

	public void setColC(ExcelField colC) {
		this.colC = colC;
	}

	public ExcelField getColD() {
		return colD;
	}

	public void setColD(ExcelField colD) {
		this.colD = colD;
	}
	
}
