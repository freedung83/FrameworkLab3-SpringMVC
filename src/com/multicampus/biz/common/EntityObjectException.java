package com.multicampus.biz.common;

/**<pre>
 *Entity Object�� ���� Exception ó��
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author �����
 *</pre>
 */
public class EntityObjectException extends PolicyInsException {

	public EntityObjectException(String message) {
		super(message);
	}

	public EntityObjectException(String errorCode, String message) {
		super(message);
        super.setErrorCode(errorCode);
	}
}
