package com.multicampus.biz.common;

/**<pre>
 *�Ϲ����� ������ ���� Exception ó��
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author �����
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