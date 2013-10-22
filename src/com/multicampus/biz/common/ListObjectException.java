package com.multicampus.biz.common;

/**<pre>
 *ListObject�� ���� Exception ó��
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author �����
 *</pre>
 */
public class ListObjectException extends PolicyInsException {

	public ListObjectException(String message) {
		super(message);
	}

	public ListObjectException(String errorCode, String message) {
		super(message);
        super.setErrorCode(errorCode);
	}
}