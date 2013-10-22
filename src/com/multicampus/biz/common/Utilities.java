package com.multicampus.biz.common;

import java.util.*;
import java.text.*;
import java.io.*;
import java.math.*;
import javax.servlet.http.*;

/**<pre>
 *유틸리티 클래스
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author 김민혁
 *</pre>
 */
public class Utilities {
    private static final int DATE_LEN = 8;
    public static final int TEL1 = 1;
    public static final int TEL2 = 2;
    public static final int TEL3 = 3;
    public static final int DATE = 1;
    public static final int YEAR = 2;
    public static final int MONTH = 3;
    public static final int DAY = 4;
    public static final int HOUR = 5;
    public static final int MINUTE = 6;
    public static final int ZIPNO1 = 1;
    public static final int ZIPNO2 = 2;
    public static final int IDNO1 = 1;
    public static final int IDNO2 = 2;
    public static final int IDNO3 = 3;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int OTA_PARTNER1 = 1;
    public static final int OTA_PARTNER2 = 2;
    public static final int OTA_PARTNER3 = 3;
    public static final int LOCALNO1 = 1;
    public static final int LOCALNO2 = 2;
    public static final int LOCALNO3 = 3;
    public static final int SHOPCODE1 = 1;
    public static final int SHOPCODE2 = 2;
    public static final int INPUTNO1 = 1;
    public static final int INPUTNO2 = 2;
    public static final int INPUTNO3 = 3;

    
    /**ListObject에 여러개의 EntityObject이 있는 경우,
     * 해당 인덱스의 EntityObject에 받은 EntityObject의 키/값을 추가시킨다.
     *@param ListObject         ListObject
     *@param EntityObject       EntityObject
     *@param 인덱스             int
     */
    public static void addValue(ListObject lo, EntityObject eo, int index)
        throws GeneralFailureException {

        if (lo == null) {
            throw new GeneralFailureException(ContextIF.MSG_011);
        }

        if (eo == null) {
            throw new GeneralFailureException(ContextIF.MSG_006);
        }

        try {
            EntityObject tmpEo = (EntityObject)lo.get(index);
        }
        catch (Exception e) {
            throw new GeneralFailureException(index + "번째 인덱스에 값이 없습니다!");
        }

        try {
            EntityObject [] eos = eo.getAll();
            for(int i=0; i<eos.length; i++) {
                ((EntityObject)lo.get(index)).put(eos[i].getKey(), eos[i].getValue());
            }
        }
        catch (PolicyInsException aceie) {}
    }

	/**ListObject에 여러개의 EntityObject이 있는 경우,
     * 해당 인덱스의 EntityObject에 받은 키/값을 추가시킨다.
     *@param ListObject         ListObject
     *@param EntityObject       EntityObject
     *@param 인덱스             int
     */
    public static void addValue(ListObject lo, String key, String value, int index)
        throws GeneralFailureException {

        if (lo == null) {
            throw new GeneralFailureException(ContextIF.MSG_011);
        }

        try {
            EntityObject tmpEo = (EntityObject)lo.get(index);
        }
        catch (Exception e) {
            throw new GeneralFailureException(index + "번째 인덱스에 값이 없습니다!");
        }

        try {
            ((EntityObject)lo.get(index)).put(key, value);
        }
        catch (PolicyInsException aceie) {}
    }

	public static String rightTrim(String str) {
        String trimStr = "";
        if ( str == null ) {
            trimStr = "";
        }
        else{
            trimStr = str.trim();
            if (trimStr.length() == 0){
                trimStr = "";
            }
            else{
                for(int i=str.length()-1; i>=0; i--){
                    if(str.charAt(i) == ' '){
                        continue;
                    }else{
                        trimStr = str.substring(0,i+1);
                        break;
                    }
                }
            }
        }
        return trimStr;
    }

	 /** 금일의 날짜시간분처를 문자열을 받음
     *@return String 객체
     */
    public static String getTodayDateTime() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new java.util.Date());
    }
    
	 /** 금일의 날짜시간분처를 문자열을 받음
     *@return String 객체
     */
    public static String getTodayDateTimeJsp() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.util.Date());
    }

/** 년 카운트만큼의 과거일자 구하기
     *@param 날짜문자열        String
     *@param Count            int
     *@return 과거년월일문자열
     */
    public static String prevYear(String date, int count)
        throws GeneralFailureException {
        Calendar c = null;
        SimpleDateFormat sdf = null;
        String tmpDate = null;
        String tmpCount = null;

        if (date.length() != DATE_LEN) {
            throw new GeneralFailureException(ContextIF.MSG_021);
        }

        sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            tmpCount = "-" + String.valueOf(count);

            c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            c.add(Calendar.YEAR, Integer.parseInt(tmpCount));
            tmpDate = String.valueOf(c.get(Calendar.YEAR));
            tmpDate = tmpDate + convertField(String.valueOf(c.get(Calendar.MONTH)+1), 2, "", "DIGIT");
            tmpDate = tmpDate + convertField(String.valueOf(c.get(Calendar.DATE)), 2, "", "DIGIT");
        }
        catch(ParseException pe) {
            throw new GeneralFailureException(ContextIF.MSG_019);
        }
        return tmpDate;
    }
    
    /** 필드값을 받아서 해당 크기 및 형태로 변경한다.
     *@param 필드값            String
     *@param 필드길이          int
     *@param 디폴트값          String
     *@param 타입(숫자,문자)   String
     *@return 변경된 필드값
     */
    public static String convertField(String _value, int length, String defaultValue, String type)
        throws GeneralFailureException {
    	
    	String value = _value;
        String retData = null;          //리턴할 데이터

        byte[] valueByte;           //값의 바이트 배열
        int valueLength;            //값의 바이트 배열 길이
        StringBuffer dataBuf;       //디폴트문자를 가질 임시 저장소
        byte [] defaultByte;        //디폴트문자의 바이트
        String defaultStr;          //디톨트문자의 문자열
        byte[] retDataByte = null;

        //필드값이 null이면 디폴트값으로 세팅
        if (value == null) {
            value = defaultValue;
        }
        //필드값이 없다면 디폴트값으로 세팅
        if (value.equals("")) {
            value = defaultValue;
        }

        try {
            //필드값만큼 byte 배열을 만든다.
            valueByte = value.getBytes(Encoding.ENCODING_KO);
            //valueByte = value.getBytes();
            //필드값의 길이를 구한다.
            valueLength = valueByte.length;

            //지정된 길이보다, 실데이터 길이가 작거나 같은경우
            if (valueLength <= length) {
                //디폴트값을 구한다. 숫자:'0', 문자:스페이스
                defaultByte = new byte[1];
                if (type.equals("DIGIT")) {
                    defaultByte[0] = (byte)'0';
                }
                else {
                    defaultByte[0] = 0x20;
                }

                defaultStr = new String(defaultByte);
                dataBuf = new StringBuffer();
                //정해진 길이-실제길이 만큼 디폴트문자열을 만든다.
                for(int i=0; i<length-valueLength; i++) {
                    dataBuf.append(defaultStr);
                }
                if (type.equals("DIGIT")) {
                    retData = dataBuf.toString() + value;
                }
                else {
                    retData = value + dataBuf.toString();
                }
            }
            //지정된 길이보다, 실데이터 길이가 큰경우
            else {
                //실데이터를 지정된 길이만큼 복사
                retDataByte = new byte[length];
                System.arraycopy(valueByte, 0, retDataByte, 0, length);
                retData = new String(retDataByte);

                //지정된길이만큼 복사했는데 복사한 데이터의 길이가 0인경우
                //예를 들어 홍길동을 3바이트복사하면 복사한 데이터가 없음
                if (retData.length() == 0) {
                    retDataByte = new byte[length-1];
                    System.arraycopy(valueByte, 0, retDataByte, 0, length-1);
                    retData = new String(retDataByte);
                }
            }
        }
        catch(UnsupportedEncodingException uee) {
            throw new GeneralFailureException(ContextIF.MSG_022);
        }
        return retData;
    }

    /** 금일을 문자열을 받음
     *@return String 객체
     */
    public static String getToday() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new java.util.Date());
    }
    
    /** 제거하기 원하는 문자를 제거한 문자열을 리턴 예) removeCharacter("2001-05-10","-") --> "20010510"
     *@param 문자열     String
     *@param 문자     String
     *@return 문자가 제거된 문자열
     */
    public static String[] removeCharacter(String[] str, String character) {
        String s[] = new String[str.length];

        for(int i=0; i<str.length; i++) {
            s[i] = removeCharacter(str[i], character);
        }
        return s;

    }
    

    /** 제거하기 원하는 문자를 제거한 문자열을 리턴 예) removeCharacter("2001-05-10","-") --> "20010510"
     *@param 문자열     String
     *@param 문자     String
     *@return 문자가 제거된 문자열
     */
    public static String removeCharacter(String str, String character) {
        StringTokenizer st = null;
        StringBuffer sb = null;
        String result = null;
        String token = null;
        char c;
        
        String fnresult = "";

        //널인경우 ""을 리턴
        if (str == null) {
        	fnresult = "";
        }else {

	        //""인경우 원데이터를 리턴
	        if (str.trim().length() == 0) {
	        	fnresult = str;
	        }else {
	
		        result = str;
		        int length = character.length();
				for(int i=0; i<length; i++) {
		            c = character.charAt(i);
		            st = new StringTokenizer(result, String.valueOf(c));
		            sb = new StringBuffer();
		            while(st.hasMoreTokens()) {
		                token = st.nextToken();
		                sb.append(token);
		            }
		            result = sb.toString();
		            fnresult = result;
		        }
	        }
        }
        
        
        return fnresult;
    }
    
    /** 월 카운트만큼의 과거일자 구하기
     *@param 날짜문자열        String
     *@param Count            int
     *@return 과거년월일문자열
     */
    public static String prevMonth(String date, int count)
        throws GeneralFailureException {
        Calendar c = null;
        SimpleDateFormat sdf = null;
        String tmpDate = null;
        String tmpCount = null;

        if (date.length() != DATE_LEN) {
            throw new GeneralFailureException(ContextIF.MSG_021);
        }

        sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            tmpCount = "-" + String.valueOf(count);

            c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            c.add(Calendar.MONTH, Integer.parseInt(tmpCount));
            tmpDate = String.valueOf(c.get(Calendar.YEAR));
            tmpDate = tmpDate + convertField(String.valueOf(c.get(Calendar.MONTH)+1), 2, "", "DIGIT");
            tmpDate = tmpDate + convertField(String.valueOf(c.get(Calendar.DATE)), 2, "", "DIGIT");
        }
        catch(ParseException pe) {
            throw new GeneralFailureException(ContextIF.MSG_019);
        }
        return tmpDate;
    }

    public static String nextMonth(String date, int count)
            throws GeneralFailureException {
            Calendar c = null;
            SimpleDateFormat sdf = null;
            String tmpDate = null;

            if (date.length() != DATE_LEN) {
                throw new GeneralFailureException(ContextIF.MSG_021);
            }

            sdf = new SimpleDateFormat("yyyyMMdd");

            try {
                c = Calendar.getInstance();
                c.setTime(sdf.parse(date));
                c.add(Calendar.MONTH, count);
                tmpDate = String.valueOf(c.get(Calendar.YEAR));
                tmpDate = tmpDate + convertField(String.valueOf(c.get(Calendar.MONTH)+1), 2, "", "DIGIT");
                tmpDate = tmpDate + convertField(String.valueOf(c.get(Calendar.DATE)), 2, "", "DIGIT");
            }
            catch(ParseException pe) {
                throw new GeneralFailureException(ContextIF.MSG_023);
            }
            return tmpDate;
        }
	
	
}

