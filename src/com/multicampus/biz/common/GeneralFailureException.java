package com.multicampus.biz.common;

/**<pre>
 *¿œπ›¿˚¿Œ ø°∑Øø° ¥Î«— Exception √≥∏Æ
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author ±ËπŒ«ı
 *</pre>
 */
public class GeneralFailureException extends PolicyInsException {
	public GeneralFailureException(String message) {
		super(message);
	}

	public GeneralFailureException(String errorCode, String message) {
		super(message);
        super.setErrorCode(errorCode);
	}

}