package com.multicampus.biz.common;

import com.multicampus.biz.common.PolicyInsException;

/**<pre>
 *데이터베이스 연결에 대한 예외처리
 *--------------------------------------
 *update history <br>
 *@since 2012/11/23
 *@author 김민혁
 *</pre>
 */
public class DBManagerException extends PolicyInsException {

	public DBManagerException(String message) {
		super(message);
	}

	public DBManagerException(String errorCode, String message) {
		super(message);
        super.setErrorCode(errorCode);
	}

	/**
	 * exception wrapper 생성자가 없어 추가함
	 * @param exception
	 * @since 2008-10-16
	 * @author DongbuCNI Chiyol Kim
	 */
	public DBManagerException(Exception exception) {
		super(exception);
	}

    /**
     * exception wrapper 생성자가 없어 추가함
     * @param exception
     * @since 2008-10-16
     * @author DongbuCNI Chiyol Kim
     */
    public DBManagerException(Exception exception, String message) {
    	super(exception, message);
    }
}