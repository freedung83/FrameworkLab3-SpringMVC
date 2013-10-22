package com.multicampus.biz.common;

import java.util.*;
import java.text.*;
import java.io.*;
import java.math.*;
import javax.servlet.http.*;

/**<pre>
 *��ƿ��Ƽ Ŭ����
 *--------------------------------------
 *update history
 *@since 2012/11/23
 *@author �����
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

    
    /**ListObject�� �������� EntityObject�� �ִ� ���,
     * �ش� �ε����� EntityObject�� ���� EntityObject�� Ű/���� �߰���Ų��.
     *@param ListObject         ListObject
     *@param EntityObject       EntityObject
     *@param �ε���             int
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
            throw new GeneralFailureException(index + "��° �ε����� ���� �����ϴ�!");
        }

        try {
            EntityObject [] eos = eo.getAll();
            for(int i=0; i<eos.length; i++) {
                ((EntityObject)lo.get(index)).put(eos[i].getKey(), eos[i].getValue());
            }
        }
        catch (PolicyInsException aceie) {}
    }

	/**ListObject�� �������� EntityObject�� �ִ� ���,
     * �ش� �ε����� EntityObject�� ���� Ű/���� �߰���Ų��.
     *@param ListObject         ListObject
     *@param EntityObject       EntityObject
     *@param �ε���             int
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
            throw new GeneralFailureException(index + "��° �ε����� ���� �����ϴ�!");
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

	 /** ������ ��¥�ð���ó�� ���ڿ��� ����
     *@return String ��ü
     */
    public static String getTodayDateTime() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new java.util.Date());
    }
    
	 /** ������ ��¥�ð���ó�� ���ڿ��� ����
     *@return String ��ü
     */
    public static String getTodayDateTimeJsp() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.util.Date());
    }

/** �� ī��Ʈ��ŭ�� �������� ���ϱ�
     *@param ��¥���ڿ�        String
     *@param Count            int
     *@return ���ų���Ϲ��ڿ�
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
    
    /** �ʵ尪�� �޾Ƽ� �ش� ũ�� �� ���·� �����Ѵ�.
     *@param �ʵ尪            String
     *@param �ʵ����          int
     *@param ����Ʈ��          String
     *@param Ÿ��(����,����)   String
     *@return ����� �ʵ尪
     */
    public static String convertField(String _value, int length, String defaultValue, String type)
        throws GeneralFailureException {
    	
    	String value = _value;
        String retData = null;          //������ ������

        byte[] valueByte;           //���� ����Ʈ �迭
        int valueLength;            //���� ����Ʈ �迭 ����
        StringBuffer dataBuf;       //����Ʈ���ڸ� ���� �ӽ� �����
        byte [] defaultByte;        //����Ʈ������ ����Ʈ
        String defaultStr;          //����Ʈ������ ���ڿ�
        byte[] retDataByte = null;

        //�ʵ尪�� null�̸� ����Ʈ������ ����
        if (value == null) {
            value = defaultValue;
        }
        //�ʵ尪�� ���ٸ� ����Ʈ������ ����
        if (value.equals("")) {
            value = defaultValue;
        }

        try {
            //�ʵ尪��ŭ byte �迭�� �����.
            valueByte = value.getBytes(Encoding.ENCODING_KO);
            //valueByte = value.getBytes();
            //�ʵ尪�� ���̸� ���Ѵ�.
            valueLength = valueByte.length;

            //������ ���̺���, �ǵ����� ���̰� �۰ų� �������
            if (valueLength <= length) {
                //����Ʈ���� ���Ѵ�. ����:'0', ����:�����̽�
                defaultByte = new byte[1];
                if (type.equals("DIGIT")) {
                    defaultByte[0] = (byte)'0';
                }
                else {
                    defaultByte[0] = 0x20;
                }

                defaultStr = new String(defaultByte);
                dataBuf = new StringBuffer();
                //������ ����-�������� ��ŭ ����Ʈ���ڿ��� �����.
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
            //������ ���̺���, �ǵ����� ���̰� ū���
            else {
                //�ǵ����͸� ������ ���̸�ŭ ����
                retDataByte = new byte[length];
                System.arraycopy(valueByte, 0, retDataByte, 0, length);
                retData = new String(retDataByte);

                //�����ȱ��̸�ŭ �����ߴµ� ������ �������� ���̰� 0�ΰ��
                //���� ��� ȫ�浿�� 3����Ʈ�����ϸ� ������ �����Ͱ� ����
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

    /** ������ ���ڿ��� ����
     *@return String ��ü
     */
    public static String getToday() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new java.util.Date());
    }
    
    /** �����ϱ� ���ϴ� ���ڸ� ������ ���ڿ��� ���� ��) removeCharacter("2001-05-10","-") --> "20010510"
     *@param ���ڿ�     String
     *@param ����     String
     *@return ���ڰ� ���ŵ� ���ڿ�
     */
    public static String[] removeCharacter(String[] str, String character) {
        String s[] = new String[str.length];

        for(int i=0; i<str.length; i++) {
            s[i] = removeCharacter(str[i], character);
        }
        return s;

    }
    

    /** �����ϱ� ���ϴ� ���ڸ� ������ ���ڿ��� ���� ��) removeCharacter("2001-05-10","-") --> "20010510"
     *@param ���ڿ�     String
     *@param ����     String
     *@return ���ڰ� ���ŵ� ���ڿ�
     */
    public static String removeCharacter(String str, String character) {
        StringTokenizer st = null;
        StringBuffer sb = null;
        String result = null;
        String token = null;
        char c;
        
        String fnresult = "";

        //���ΰ�� ""�� ����
        if (str == null) {
        	fnresult = "";
        }else {

	        //""�ΰ�� �������͸� ����
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
    
    /** �� ī��Ʈ��ŭ�� �������� ���ϱ�
     *@param ��¥���ڿ�        String
     *@param Count            int
     *@return ���ų���Ϲ��ڿ�
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

