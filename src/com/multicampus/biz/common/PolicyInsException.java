package com.multicampus.biz.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**<pre>
 *���縮��ũ�����ý��� Root Exception Ŭ����
 *--------------------------------------
 *update history <br>
 *@since 2013-02-15
 *@author �����
 *</pre>
 */
public class PolicyInsException extends Exception implements java.io.Serializable {

	public PolicyInsException(String message) {
		super(message);
	}

	public PolicyInsException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public PolicyInsException(Exception exception) {
		super(exception);
	}

	public PolicyInsException(Exception exception, String message) {
		super(message, exception);
	}

	/*override*/
	public String getMessage() {
		StringBuffer buf = new StringBuffer();

		String message = super.getMessage();
		if (null != message) buf.append(message);

		if (null != getCause()) {
			Throwable t = getRootCause();
			message = t.getMessage();
			if (null != message) {
				buf.append("\ncause: ");
				buf.append(message);
			}
		}
		return buf.toString();
	}

	/*override*/
	public String toString() {
		return getMessage();
	}

	public Throwable getRootCause() {
		return getRootCause(this);
	}

	public static Throwable getRootCause(Throwable _t) {
		Throwable t = _t;
		Throwable cause = t.getCause();
		while (null != cause) {
			t = cause;
		}
		return t;
	}

	public static String getStackTraceAsString(Throwable t) {
		StringWriter wr = new StringWriter();
		t.printStackTrace(new PrintWriter(wr));
		return (wr.toString());
	}

	public String getStackTraceAsString() {
		return getStackTraceAsString(this);
	}

	/**
	 * ���ʿ��� �޼ҵ��̳� �����ϴ� �Ϻ� Ŭ������ �����Ƿ� ���ӽ�Ŵ
	 * @return
	 */
	public String getErrorMessage() {
		return getMessage();
	}

	/**
	 * ���ʿ��� �޼ҵ��̳� �����ϴ� �Ϻ� Ŭ������ �����Ƿ� ���ӽ�Ŵ
	 * @return
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * ���ʿ��� �޼ҵ��̳� �����ϴ� �Ϻ� Ŭ������ �����Ƿ� ���ӽ�Ŵ
	 */
	public String getErrorCode() {
		return errorCode;
	}

	private static final long serialVersionUID = -8814565523139764073L;
	private String errorCode = "";	//����� ���ǵ� �����ڵ�
}
