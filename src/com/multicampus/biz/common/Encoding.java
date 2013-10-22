package com.multicampus.biz.common;

import java.io.*;

/**<pre>
 *한글->영어, 영어->한글 인코딩 변환 클래스 
 *-------------------------------------- 
 *update history <br>
 *@since 2012/11/23
 *@author 김민혁
 *</pre>
 */		
public class Encoding {
	/** 한글 인코딩 */
	public static final String ENCODING_KO = "KSC5601";     
	/** 영어 인코딩 */
	public static final String ENCODING_EN = "8859_1";	    
	
	/** 영어에서 한글로 인코딩 변환
	*@param 영어문자열     String
	*@return 한글문자열
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
	
    
	/** 한글에서 영어로 인코딩 변환
	*@param 한글문자열     String
	*@return 영어 문자열
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