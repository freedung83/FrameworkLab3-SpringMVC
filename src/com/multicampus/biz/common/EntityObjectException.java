package com.multicampus.biz.common;

/**<pre>
 *Entity Objectø° ¥Î«— Exception √≥∏Æ
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author ±ËπŒ«ı
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
