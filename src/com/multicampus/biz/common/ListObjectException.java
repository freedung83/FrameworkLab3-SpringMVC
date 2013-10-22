package com.multicampus.biz.common;

/**<pre>
 *ListObjectø° ¥Î«— Exception √≥∏Æ
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author ±ËπŒ«ı
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