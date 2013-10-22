package com.multicampus.biz.main.vo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POSXTR_ADMIN_MENU")
public class MainVO {
	@Id
	private String EMPLOYEE_NUMBER;
	
	private String CD_V;
	private String MENU_NAME;
	private String DOCUMENT_NO;
	private String FK_CD_TP;
	private String URL;
	private Date CREATION_DATE = new Date(System.currentTimeMillis());
	private Date LAST_UPDATE_DATE = new Date(System.currentTimeMillis());
	private String DELETE_FLAG;
	
	public String getEMPLOYEE_NUMBER() {
		return EMPLOYEE_NUMBER;
	}

	public void setEMPLOYEE_NUMBER(String eMPLOYEENUMBER) {
		EMPLOYEE_NUMBER = eMPLOYEENUMBER;
	}

	public String getCD_V() {
		return CD_V;
	}

	public void setCD_V(String cDV) {
		CD_V = cDV;
	}

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENUNAME) {
		MENU_NAME = mENUNAME;
	}

	public String getDOCUMENT_NO() {
		return DOCUMENT_NO;
	}

	public void setDOCUMENT_NO(String dOCUMENTNO) {
		DOCUMENT_NO = dOCUMENTNO;
	}

	public String getFK_CD_TP() {
		return FK_CD_TP;
	}

	public void setFK_CD_TP(String fKCDTP) {
		FK_CD_TP = fKCDTP;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Date getCREATION_DATE() {
		return CREATION_DATE;
	}

	public void setCREATION_DATE(Date cREATIONDATE) {
		CREATION_DATE = cREATIONDATE;
	}

	public Date getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}

	public void setLAST_UPDATE_DATE(Date lASTUPDATEDATE) {
		LAST_UPDATE_DATE = lASTUPDATEDATE;
	}

	public String getDELETE_FLAG() {
		return DELETE_FLAG;
	}

	public void setDELETE_FLAG(String dELETEFLAG) {
		DELETE_FLAG = dELETEFLAG;
	}

	@Override
	public String toString() {
		return "MainVO [CD_V=" + CD_V + ", MENU_NAME=" + MENU_NAME + ", DOCUMENT_NO="
				+ DOCUMENT_NO + ", FK_CD_TP=" + FK_CD_TP + ", URL=" + URL
				+ ", CREATION_DATE=" + CREATION_DATE + ", LAST_UPDATE_DATE=" + LAST_UPDATE_DATE
				+ ", DELETE_FLAG=" + DELETE_FLAG + "]";
	}
}
