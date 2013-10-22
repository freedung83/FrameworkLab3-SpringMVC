package com.multicampus.biz.user.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POSXTR_ADMIN_USER")
public class UserVO {
	@Id
	private String EMPLOYEE_NUMBER;
	private String EMPLOYEE_NAME;

	public String getEMPLOYEE_NUMBER() {
		return EMPLOYEE_NUMBER;
	}
	public void setEMPLOYEE_NUMBER(String eMPLOYEENUMBER) {
		EMPLOYEE_NUMBER = eMPLOYEENUMBER;
	}
	public String getEMPLOYEE_NAME() {
		return EMPLOYEE_NAME;
	}
	public void setEMPLOYEE_NAME(String eMPLOYEENAME) {
		EMPLOYEE_NAME = eMPLOYEENAME;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EMPLOYEE_NUMBER == null) ? 0 : EMPLOYEE_NUMBER.hashCode());
		result = prime * result + ((EMPLOYEE_NAME == null) ? 0 : EMPLOYEE_NAME.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (EMPLOYEE_NUMBER == null) {
			if (other.EMPLOYEE_NUMBER != null)
				return false;
		} else if (!EMPLOYEE_NUMBER.equals(other.EMPLOYEE_NUMBER))
			return false;
		if (EMPLOYEE_NAME == null) {
			if (other.EMPLOYEE_NAME != null)
				return false;
		} else if (!EMPLOYEE_NAME.equals(other.EMPLOYEE_NAME))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + EMPLOYEE_NUMBER + ", name=" + EMPLOYEE_NAME
				+ "]";
	}	
}
