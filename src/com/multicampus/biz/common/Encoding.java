package com.multicampus.biz.common;

import java.io.*;

/**<pre>
 *�ѱ�->����, ����->�ѱ� ���ڵ� ��ȯ Ŭ���� 
 *-------------------------------------- 
 *update history <br>
 *@since 2012/11/23
 *@author �����
 *</pre>
 */		
public class Encoding {
	/** �ѱ� ���ڵ� */
	public static final String ENCODING_KO = "KSC5601";     
	/** ���� ���ڵ� */
	public static final String ENCODING_EN = "8859_1";	    
	
	/** ����� �ѱ۷� ���ڵ� ��ȯ
	*@param ����ڿ�     String
	*@return �ѱ۹��ڿ�
	*/
    public static String en2ko(String en) {
    	String ko = "";
    	
        if (en == null) {
        	ko = "";
        }else {
        	if (en.equals("")) {
        		ko = "";
        	}else {
        		try {
                    ko = new String(en.getBytes(ENCODING_EN), ENCODING_KO);
                }
        		catch(UnsupportedEncodingException e) {
        			ko = "";
        		}
                
        	}
            
        }
        return ko;
        
    }  
	
    
	/** �ѱۿ��� ����� ���ڵ� ��ȯ
	*@param �ѱ۹��ڿ�     String
	*@return ���� ���ڿ�
	*/
	public static String ko2en(String ko) {
		String en = "";
        if (ko == null) { 
        	en = "";
        }else {
        	if (ko.equals("")) { 
        		en = "";
        	}else {        		
                try {
                    en = new String(ko.getBytes(ENCODING_KO), ENCODING_EN);
                }
        		catch(UnsupportedEncodingException e) {
        			en = "";
        		}
        	}
    		
        }
        
        return en;
    } 
}