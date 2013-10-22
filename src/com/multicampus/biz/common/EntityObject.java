package com.multicampus.biz.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**<pre>
 *EntityObject 클래스는 여러 key/value쌍을 저장하는 클래스이다
 *get 메소드를 이용하여 해당 key의 value를 읽을수 있으며,
 *put 메소드를 이용하여 key와 value를 EntityObject 객체에 저장한다
 *----------------------------------------------------------------
 *update history <br>
 *@since 2012/11/23
 */
public class EntityObject implements Serializable {
	/** Entity Object의 key/value를 저장하는 해쉬테이블 */
	Hashtable ht = null;
	/** Entity Object의 key값 */
	String key = "";
	/** Entity Object의 value값 */
	Object value = "";

	static final long serialVersionUID = -8814565523139764073L;

	public static final int YES = 1;	//EntityObjectException 발생함
	public static final int NO = 2;		//EntityObjectException 발생안함 : '' empty String으로 세팅
	public static final int ZERO = 3;	//EntityObjectException 발생안함 : 0으로 세팅

	/** Entity Object의 생성자
	 */
	public EntityObject() {
		ht = new Hashtable();
	}

	/** 해당 key값의 value를 읽는다. 해당 key가 없는경우 예외가 발생된다.
	 *@param key값             String
	 *@return Object 객체
	 */
	public Object get(String _key) throws EntityObjectException {
		String tmpKey = _key;
		try	{
			if (tmpKey == null) {
				//tmpKey값이 null입니다.
				throw new EntityObjectException(ContextIF.MSG_017);
			}

			//tmpKey값을 대문자로 변환
			tmpKey = tmpKey.toUpperCase();
			//해당 tmpKey값이 HashTable에 있는가?
			if (ht.containsKey(tmpKey) == true) {
				return (Object)ht.get(tmpKey);
			}
			else {
				//해당 tmpKey의 value가 없습니다.
				throw new EntityObjectException("해당 tmpKey("+tmpKey+")의 value가 없습니다.");
			}
		}
		catch(EntityObjectException eoe) {
			throw eoe;
		}
		catch(Exception e) {
			//해당 tmpKey의 value 읽기중 에러가 발생하였습니다.
			throw new EntityObjectException("해당 tmpKey("+tmpKey+")의 value 읽기중 에러가 발생하였습니다.");
		}
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * @param key
	 * @return
	 * @throws EntityObjectException
	 */
	public String getStr(String key) throws EntityObjectException {
		return (String)get(key);
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * @param key
	 * @param defVal
	 * @return
	 * @throws EntityObjectException
	 */
	public String getStr(String key, String defVal) {
		String result = "";
		if (isExistKey(key)) {
			try {
				result= (String)get(key);
			} catch (EntityObjectException e) {
			}
		}else {
			result = defVal;
		}
		return result;
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @return
	 * @throws EntityObjectException
	 * @throws NumberFormatException
	 */
	public int getInt(String key) throws EntityObjectException {
		int result = 0;
		Object obj = get(key);
		if (obj instanceof BigDecimal) {
			result = ((BigDecimal)obj).intValue();
		} else {

			if (obj instanceof Long) {
				result = ((Long) obj).intValue();
			} else {

				if (obj instanceof Integer) {
					result = ((Integer) obj).intValue();
				} else {

					if (obj instanceof Float) {
						result = ((Float) obj).intValue();
					} else {

						if (obj instanceof Double) {
							result = ((Double) obj).intValue();
						} else {
							String val = obj.toString();
							result = Integer.parseInt(val.trim());
						}
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @param defVal
	 * @return
	 * @throws EntityObjectException
	 */
	public int getInt(String key, int defVal) throws EntityObjectException {
		int result = 0;
		if (isExistKey(key)) {
			try {
				result= getInt(key);
			} catch (NumberFormatException e) {
			}
		}else {
			result = defVal; 
		}
		return result;
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @return
	 * @throws EntityObjectException
	 * @throws NumberFormatException
	 */
	public long getLong(String key) throws EntityObjectException {
		long result = 0;
		Object obj = get(key);
		if (obj instanceof BigDecimal) {
			result = ((BigDecimal)obj).longValue();
		}else { 
		
			if (obj instanceof Long) {
				result = ((Long)obj).longValue();
			}else { 
			
				if (obj instanceof Integer) {
					result = ((Integer)obj).longValue();
				}else { 
				
					if (obj instanceof Float) {
						result = ((Float)obj).longValue();
					}else {
					
						if (obj instanceof Double) {
							result = ((Double)obj).longValue();
							
						}else {
							String val = obj.toString();
							result = Long.parseLong(val.trim());
						}
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @param defVal
	 * @return
	 * @throws EntityObjectException
	 */
	public long getLong(String key, long defVal) throws EntityObjectException {
		
		long result = 0;
		
		if (isExistKey(key)) {
			try {
				result =  getLong(key);
			} catch (NumberFormatException e) {
			}
		}else {
			result = defVal;
		}	
		
		return result;
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * modified date: 2008.10.28 DIT 김치열
	 * @param key
	 * @return
	 * @throws EntityObjectException
	 * @throws NumberFormatException
	 */
	public BigDecimal getBigDecimal(String key) throws EntityObjectException {
		String val = (String)getStr(key);
		return new BigDecimal(val.trim());
	}

	/**
	 * 타입캐스팅이 번거로우므로 헬퍼함수를 만든다
	 * modified date: 2008.10.28 DIT 김치열
	 * @param key
	 * @param defVal
	 * @return
	 * @throws EntityObjectException
	 */
	public BigDecimal getBigDecimal(String key, String defVal) throws EntityObjectException {
		BigDecimal result = null;
		if (isExistKey(key)) {
			try {
				result =  new BigDecimal(getStr(key));
			} catch (NumberFormatException e) {
			}
		}else {
			result = new BigDecimal(defVal);
		}
		 
		return result;
	}

	/**
	 * 키값에 해당하는 값과 파라미터 값과 비교
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal) throws EntityObjectException {
		Object val = get(key);
		return val.equals(compVal);
	}

	/**
	 * 키값에 해당하는 값과 파라미터 값과 비교
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal1, Object compVal2) throws EntityObjectException {
		return (valueEquals(key, compVal1) || valueEquals(key, compVal2));
	}

	/**
	 * 키값에 해당하는 값과 파라미터 값과 비교
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal1, Object compVal2, Object compVal3) throws EntityObjectException {
		return (valueEquals(key, compVal1) || valueEquals(key, compVal2) || valueEquals(key, compVal3));
	}

	/**
	 * 키값에 해당하는 값과 파라미터 값과 비교
	 * modified date: 2007.09.13 DIT 김치열
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal1, Object compVal2, Object compVal3, Object compVal4) throws EntityObjectException {
		return (valueEquals(key, compVal1) || valueEquals(key, compVal2) ||
				valueEquals(key, compVal3) || valueEquals(key, compVal4));
	}

	/** 해당 key값의 value를 읽는다. 해당 key가 없으면 디폴트를 문자로 보고 "" 값을 리턴한다.
	 * @param key값                     String
	 * @param Exception발생여부         int
	 * @return Object
	 */
	public Object get(String _key, int isThrowEx) {
		
		Object result = null;
		String tmpKey = _key;
		
		try	{
			//Exception을 발생해야 하는가?
			if (isThrowEx == YES) {
				result = get(tmpKey);
			}else {
				if (tmpKey == null) {
					result = "";
				}else {
					//tmpKey값을 대문자로 변환
					tmpKey = tmpKey.toUpperCase();
					//해당 tmpKey값이 HashTable에 있는가?
					if (ht.containsKey(tmpKey) == true) {
						if (isThrowEx == ZERO && ((String)ht.get(tmpKey)).equals("")){
							//Debug.println(" ZERO CASE 1 : empty string");
							result = "0";
						}
						else if (isThrowEx == ZERO && (ht.get(tmpKey) == null)){
							//Debug.println(" ZERO CASE 2 : null");
							result = "0";
						}
						else{
							result = (Object)ht.get(tmpKey);
							if( result != null && result.equals("null")) {
								result = null;
							}
						}
					}
					else {
						if (isThrowEx == ZERO){
							//Debug.println(" ZERO CASE 3 : not exist in hashTable");
							result = "0";
						}
						else{
							result = "";
						}
					}
				}
				
			}
			
		}
		catch(Exception e)	{
			result = "";
		}
		
		return result;
	}

	/** 해당 key값의 value를 읽는다. 해당 key가 없으면 디폴트값으로 리턴한다.
	 * @param key값                     String
	 * @param Exception발생여부          int
	 * @param 디폴트값					String
	 * @return Object
	 */
	public Object get(String _key, int isThrowEx, String defaultValue) {
		
		String tmpKey = _key;
		Object obj = null;
		try	{
			//Exception을 발생해야 하는가?
			if (isThrowEx == YES) {
				obj = get(tmpKey);
			}else {
				if (tmpKey == null) {
					obj = defaultValue;
				}else {

					//key값을 대문자로 변환
					tmpKey = tmpKey.toUpperCase();
					//해당 key값이 HashTable에 있는가?
					if (ht.containsKey(tmpKey) == true) {
						//return (String)ht.get(key);
						obj = (Object)ht.get(tmpKey);
					}
					else {
						obj = defaultValue;
					}
				}

				
			}

					}
		catch(Exception e)	{
			obj = defaultValue;
		}
		
		return obj;
	}


	/** EntityObject에 key와 value값을 저장한다
	 *@param key문자열     String
	 *@param value문자열   Object
	 */
	public void put(String _key, Object value)
		throws EntityObjectException {
		String tmpKey = _key;
		try	{
			if (tmpKey == null) {
				//tmpKey값이 null입니다.
				throw new EntityObjectException(ContextIF.MSG_017);
			}
			if (tmpKey.trim().length() == 0) {
				//tmpKey값이 없습니다.
				throw new EntityObjectException(ContextIF.MSG_018);
			}
			//tmpKey값을 대문자로 변환
			tmpKey = tmpKey.toUpperCase();
			//해쉬테이블에 저장
			ht.put(tmpKey, value);
		}
		catch(EntityObjectException eoe) {
			throw eoe;
		}
		catch(Exception e) {
			//해당 tmpKey의 value 저장중 에러가 발생하였습니다.
			throw new EntityObjectException("해당 tmpKey("+tmpKey+")의 value("+value+") 저장중 에러가 발생하였습니다.");
		}
	}

	public void put(String key, int value) throws EntityObjectException {
		put(key, Integer.toString(value));
	}

	public void put(String key, long value) throws EntityObjectException {
		put(key, Long.toString(value));
	}

	/** key와 value값을 저장한다
	 *세번째 아규먼트가 YES인경우 null을 ""로 저장한다.
	 *@param key문자열     String
	 *@param value문자열   Object
	 *@param 값에대한null허용여부  int
	 */
	public void put(String _key, Object _value, int isNull)
		throws EntityObjectException {
		String tmpKey   = _key;
		Object tmpValue = _value;
		try	{
			//null을 허용하는가?
			if (isNull == NO) {
				put(tmpKey, tmpValue);
			}

			if (tmpKey == null) {
				//tmpKey값이 null입니다.
				throw new EntityObjectException(ContextIF.MSG_017);
			}
			if (tmpKey.trim().length() == 0) {
				//tmpKey값이 없습니다.
				throw new EntityObjectException(ContextIF.MSG_018);
			}
			//tmpKey값을 대문자로 변환
			tmpKey = tmpKey.toUpperCase();

			if (tmpValue == null) {
				tmpValue = "";
			}
			else if(isNull == YES && (tmpValue.toString().toUpperCase()).equals("NULL")) {
				tmpValue = "";
			}
			//해쉬테이블에 저장
			ht.put(tmpKey, tmpValue);
		}
		catch(EntityObjectException eoe) {
			throw eoe;
		}
		catch(Exception e) {
			//해당 tmpKey의 tmpValue 저장중 에러가 발생하였습니다.
			throw new EntityObjectException("해당 tmpKey("+tmpKey+")의 tmpValue("+tmpValue+") 저장중 에러가 발생하였습니다.");
		}
	}

	/**해당 key를 EntityObject에서 삭제한다
	 *@param key문자열     String
	 *@return 없음
	 */
	public void remove(String _key) throws EntityObjectException {
		Object o = null;
		String tmpkey = _key.toUpperCase();
		o = ht.remove(tmpkey);

		if (o == null) {
			//Debug.println("해당 key값이 없으므로 삭제할수 없습니다. : " + key);
			//해당 key값이 없으므로 삭제할수 없습니다.
			throw new EntityObjectException(ContextIF.MSG_029);
		}
	}

	/** EntityObject 객체에 해당 key가 있는가?
	 *@param key값			String
	 *@return boolean
	 */
	public boolean isExistKey(String _key) {
		String tmpkey = _key.toUpperCase();
		return ht.containsKey(tmpkey);
	}

	/**
	 * key 존재 여부를 체크
	 * @param keys
	 * @return
	 */
	public void checkExistKey(String keys) throws EntityObjectException {
		if (!isExistKey(keys)) {
			throw new EntityObjectException("해당 key(" + keys + ")의 value가 없습니다.");
		}
	}

	/**
	 * key 존재 여부를 체크
	 * @param keys
	 * @return
	 */
	public void checkExistKey(String[] keys) throws EntityObjectException {
		for (int i = 0; i < keys.length; i++) {
			if (!isExistKey(keys[i])) {
				throw new EntityObjectException("해당 key(" + keys[i] + ")의 value가 없습니다.");
			}
		}
	}

	/** EntityObject 객체의 사이즈를 리턴한다
	 *@return EntityObject 객체의 사이즈
	 */
	public int size() {
		return ht.size();
	}

	/** Entity Object를 모든 데이터를 clear시킴
	 */
	public void clear() {
		ht.clear();
	}

	/** EntityObject의 clone을 생성
	 * @return Object 객체
	 */
	public synchronized Object clone() {
		EntityObject eoClone;
		try	{
			//1. Clonable을 이용하는 방법
			//eoClone = new EntityObject();
			//eoClone = (EntityObject)super.clone();

			//2. Serializable을 이용하는 방법
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			eoClone = (EntityObject)ois.readObject();
			ois.close();
		}
		catch(Exception e) {
		eoClone = null;
		}
		return eoClone;
	}

	/**EntityObject 객체의 모든 key와 value를
	 * EntityObject 배열로 변환하여 리턴한다.
	 *@return EntityObject 배열
	 */
	public EntityObject[] getAll() throws EntityObjectException {
		List v = null;
		EntityObject [] eos = null;
		EntityObject eo = null;
		Enumeration keys = null;
		Enumeration values = null;
		try	{
			//해쉬테이블의 key 리스트를 구한다.
			keys = ht.keys();
			//해쉬테이블의 value 리스트를 구한다.
			values = ht.elements();
			//Vector 객체 생성
			v = new ArrayList();
			while(keys.hasMoreElements()) {
				eo = new EntityObject();
				eo.setKey((String) keys.nextElement());
				eo.setValue(values.nextElement());				
				v.add(eo);
			}
			eos = new EntityObject[v.size()];
			for (int i=0, j=eos.length-1; i<eos.length; i++, j--) {
				eos[j] = (EntityObject)v.get(i);
			}
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
		return eos;
	}

	/**EntityObject의 모든 key와 value를 "key : value"형태로
	 * 콘솔에 로그를 출력한다
	 */
	public void println() throws EntityObjectException {
		EntityObject[] eoList = null;
		try {
			//EntityObject의 객체를 EntityObject 배열로 받는다
			eoList = getAll();
			for(int i=0; i<eoList.length; i++) {
			}
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
	}

	/**EntityObject의 모든 key와 value를 "key : value"형태로
	 * 콘솔에 로그를 출력한다
	 */
	public void print() throws EntityObjectException {
	}

	/**EntityObject의 모든 key와 value를 "key : value"형태로
	 * 콘솔에 로그를 출력한다
	 */
	public String  getPrintString() throws EntityObjectException {
		EntityObject[] eoList = null;
		StringBuffer buffer = null;
		try {
			//EntityObject의 객체를 EntityObject 배열로 받는다
			eoList = getAll();

			buffer = new StringBuffer();
			buffer.append("********************EntityObject start*****************");
			buffer.append(System.getProperty("line.separator"));
			buffer.append("[EntityObject(사이즈 = " + eoList.length + ")]");
			buffer.append(System.getProperty("line.separator"));
			for(int i=0; i<eoList.length; i++) {
				buffer.append("#" + i + " 번째 인덱스 : ");
				if (eoList[i].getValue().getClass().isArray())	{
					buffer.append("사이즈 = ");
					if (eoList[i].getValue().getClass().getComponentType().isPrimitive()) {
						buffer.append((new ToStringGenerator()).getPrimitiveArrayFieldData(eoList[i].getValue(), ""));
					}
					else {
						buffer.append((new ToStringGenerator()).getNonPrimitiveArrayFieldData(eoList[i].getValue(), ""));
					}
				}
				else {
					buffer.append("[" + eoList[i].getKey() + " = " + eoList[i].getValue() + "]");
				}
				buffer.append(System.getProperty("line.separator"));
				/*
				if (i!=(eoList.length-1)) {
					buffer.append(",");
				}
				if (((i+1)%5)==0) {

				}
				*/
			}
			buffer.append("********************EntityObject end*******************");
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
		return buffer.toString();
	}


	/** Entity Object의 모든 key와 value를 HTML로 리턴
	 *@return HTML 문자열
	 */
	public String printHtml() throws EntityObjectException {
		return printHtml("");
	}

	/** Entity Object의 모든 key와 value를 HTML로 리턴
	 *@return HTML 문자열
	 */
	public String printHtml(String title) throws EntityObjectException {
		StringBuffer sb = null;
		EntityObject[] eoList = null;
		try	{
			//EntityObject의 객체를 EntityObject 배열로 받는다
			eoList = getAll();

			sb = new StringBuffer();
			if (title.equals("")) {
				sb.append("<b>EntityObject List</b>\n");
			}
			else {
				sb.append("<b>&nbsp;" + title + "&nbsp;</b>\n");
			}

			sb.append("<table border=\"1\" bordercolorlight=\"#CCCCCC\" bordercolordark=\"#FFFFFF\">\n");
			sb.append("<tr>\n");
			sb.append("<td align=\"center\">KEY </td>\n");
			sb.append("<td align=\"center\">VALUE </td>\n");
			sb.append("</tr>\n");
			for(int i=0; i<eoList.length; i++) {
				sb.append("<tr>\n");
				sb.append("<td align=\"left\">" + eoList[i].getKey() + "</td>\n");
				sb.append("<td align=\"left\">&nbsp;" + eoList[i].getValue() + "</td>\n");
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
		return sb.toString();
	}

	/** key값 읽기
	 *@return key 문자열
	 */
	public String getKey() {
		return this.key;
	}

	/** key값 저장
	 *@param key 문자열
	 */
	private void setKey (String key) {
		this.key = key;
	}

	/** value값 읽기
	 *@return value 문자열
	 */
	public Object getValue() {
		return this.value;
	}

	/** value값 저장
	 *@param value 문자열
	 */
	private void setValue (Object value) {
		this.value = value;
	}

	/** key값 배열 읽기
	 *@return key 문자열 배열
	 */
	public String[] getKeys() throws EntityObjectException {
		Enumeration keys = null;
		String [] keysStr = null;
		int i = 0;
		try {
			//해쉬테이블의 key 리스트를 구한다.
			keys = ht.keys();
			keysStr = new String[ht.size()];
			while(keys.hasMoreElements()) {
				keysStr[i] = (String) keys.nextElement();
				i = i + 1;
			}
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
		return keysStr;
	}

	/**
	 * 파라미터 값에 새로운 값을 모두 더한다.
	 * @param returnEo
	 * @return
	 * @throws EntityObjectException
	 */
	public EntityObject putAll(EntityObject returnEo) throws EntityObjectException {
		Enumeration keys = null;
		Enumeration values = null;
		try	{
			//해쉬테이블의 key 리스트를 구한다.
			keys = ht.keys();
			//해쉬테이블의 value 리스트를 구한다.
			values = ht.elements();
			while(keys.hasMoreElements()) {
				returnEo.put((String)keys.nextElement(), values.nextElement());
			}
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
		return returnEo;
	}


	/** EntityObject 테스트용 메소드
	 */
	public static void main(String args[]) {
		try	{
			/*
			String[] productId = {"P111", "P112"};
			EntityObject eo = new EntityObject();
			eo.put("productId", productId);
			eo.put("cartId", "1");

			String[] tmpProd = (String[])eo.get("productId");
			for(int i=0; i<tmpProd.length; i++)
				Debug.println(tmpProd[i]);
			eo.print();
			*/
			/*
			ListObject lo = new ListObject();
			String[] a = {"1", "2", "3"};
			String[] b = {"가", "나", "다"};
			EntityObject oriEo = new EntityObject();
			oriEo.put("a", a);
			oriEo.put("b", b);

			Debug.println(a.toString());

			EntityObject[] eos = null;
			int size = 0;
			EntityObject eo = null;
			String key = null;
			String[] value = null;

			eos = oriEo.getAll();
			if (eos.length>0) {
				lo = new ListObject();
				size = ((String[])eos[0].getValue()).length;
				for(int j=0; j<size; j++) {
					eo = new EntityObject();
					for(int i=0; i<eos.length; i++) {
						key = eos[i].getKey();
						value = (String[])eos[i].getValue();
						eo.put(key, value[j]);
					}
					lo.add(eo);
				}

				for(int i=0; i<lo.size(); i++) {
					((EntityObject)lo.get(i)).print();
				}
			}


			String[] arr = {"1", "2", "3"};
			String a = "1";
			String b = "가";
			EntityObject eo = new EntityObject();
			eo.put("a", a);
			eo.put("b", b);
			eo.put("c", arr);
			eo.put("d", b);
			eo.put("e", b);
			eo.put("f", b);
			eo.put("g", arr);
			eo.put("h", b);
			eo.print();
			//Debug.println(eo);


			EntityObject eo = new EntityObject();
			Double d = new Double("100000.0");

			eo.put("a", d);
			Debug.println(eo.get("a", EntityObject.NO));



			EntityObject eo = new EntityObject();
			Double d = new Double("100000.0");
			eo.put("a", d);

			EntityObject eo1 = (EntityObject)eo.clone();
			eo.put("b", d);
			eo.print();
			eo1.print();
			*/
			EntityObject eo = new EntityObject();
			eo.put("aa", "null", EntityObject.YES);
			eo.put("bb", "null");
			eo.put("cc", "하하", EntityObject.YES);
			eo.put("dd", "호호");

			//String[] str = new String[4];
			//str= eo.getKeys();


			//Debug.println ("degit = " + Utilities.convertField("1", 5, "", "DIGIT") + "char = " + Utilities.convertField("1", 5, "", "CAHR") );

			//Debug.println(eo.get("a", EntityObject.NO, "1231"));

		}
		catch(EntityObjectException eoe) {
			eoe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
