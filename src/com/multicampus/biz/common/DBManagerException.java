package com.multicampus.biz.common;

import com.multicampus.biz.common.PolicyInsException;

/**<pre>
 *�����ͺ��̽� ���ῡ ���� ����ó��
 *--------------------------------------
 *update history <br>
 *@since 2012/11/23
 *@author �����
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
	 * exception wrapper �����ڰ� ���� �߰���
	 * @param exception
	 * @since 2008-10-16
	 * @author DongbuCNI Chiyol Kim
	 */
	public DBManagerException(Exception exception) {
		super(exception);
	}

    /**
     * exception wrapper �����ڰ� ���� �߰���
     * @param exception
     * @since 2008-10-16
     * @author DongbuCNI Chiyol Kim
     */
    public DBManagerException(Exception exception, String message) {
    	super(exception, message);
    }
}