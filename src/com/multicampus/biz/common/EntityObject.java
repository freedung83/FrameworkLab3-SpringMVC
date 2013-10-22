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
 *EntityObject Ŭ������ ���� key/value���� �����ϴ� Ŭ�����̴�
 *get �޼ҵ带 �̿��Ͽ� �ش� key�� value�� ������ ������,
 *put �޼ҵ带 �̿��Ͽ� key�� value�� EntityObject ��ü�� �����Ѵ�
 *----------------------------------------------------------------
 *update history <br>
 *@since 2012/11/23
 */
public class EntityObject implements Serializable {
	/** Entity Object�� key/value�� �����ϴ� �ؽ����̺� */
	Hashtable ht = null;
	/** Entity Object�� key�� */
	String key = "";
	/** Entity Object�� value�� */
	Object value = "";

	static final long serialVersionUID = -8814565523139764073L;

	public static final int YES = 1;	//EntityObjectException �߻���
	public static final int NO = 2;		//EntityObjectException �߻����� : '' empty String���� ����
	public static final int ZERO = 3;	//EntityObjectException �߻����� : 0���� ����

	/** Entity Object�� ������
	 */
	public EntityObject() {
		ht = new Hashtable();
	}

	/** �ش� key���� value�� �д´�. �ش� key�� ���°�� ���ܰ� �߻��ȴ�.
	 *@param key��             String
	 *@return Object ��ü
	 */
	public Object get(String _key) throws EntityObjectException {
		String tmpKey = _key;
		try	{
			if (tmpKey == null) {
				//tmpKey���� null�Դϴ�.
				throw new EntityObjectException(ContextIF.MSG_017);
			}

			//tmpKey���� �빮�ڷ� ��ȯ
			tmpKey = tmpKey.toUpperCase();
			//�ش� tmpKey���� HashTable�� �ִ°�?
			if (ht.containsKey(tmpKey) == true) {
				return (Object)ht.get(tmpKey);
			}
			else {
				//�ش� tmpKey�� value�� �����ϴ�.
				throw new EntityObjectException("�ش� tmpKey("+tmpKey+")�� value�� �����ϴ�.");
			}
		}
		catch(EntityObjectException eoe) {
			throw eoe;
		}
		catch(Exception e) {
			//�ش� tmpKey�� value �б��� ������ �߻��Ͽ����ϴ�.
			throw new EntityObjectException("�ش� tmpKey("+tmpKey+")�� value �б��� ������ �߻��Ͽ����ϴ�.");
		}
	}

	/**
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * @param key
	 * @return
	 * @throws EntityObjectException
	 */
	public String getStr(String key) throws EntityObjectException {
		return (String)get(key);
	}

	/**
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
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
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * modified date: 2007.09.13 DIT ��ġ��
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
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * modified date: 2007.09.13 DIT ��ġ��
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
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * modified date: 2007.09.13 DIT ��ġ��
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
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * modified date: 2007.09.13 DIT ��ġ��
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
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * modified date: 2008.10.28 DIT ��ġ��
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
	 * Ÿ��ĳ������ ���ŷο�Ƿ� �����Լ��� �����
	 * modified date: 2008.10.28 DIT ��ġ��
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
	 * Ű���� �ش��ϴ� ���� �Ķ���� ���� ��
	 * modified date: 2007.09.13 DIT ��ġ��
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
	 * Ű���� �ش��ϴ� ���� �Ķ���� ���� ��
	 * modified date: 2007.09.13 DIT ��ġ��
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal1, Object compVal2) throws EntityObjectException {
		return (valueEquals(key, compVal1) || valueEquals(key, compVal2));
	}

	/**
	 * Ű���� �ش��ϴ� ���� �Ķ���� ���� ��
	 * modified date: 2007.09.13 DIT ��ġ��
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal1, Object compVal2, Object compVal3) throws EntityObjectException {
		return (valueEquals(key, compVal1) || valueEquals(key, compVal2) || valueEquals(key, compVal3));
	}

	/**
	 * Ű���� �ش��ϴ� ���� �Ķ���� ���� ��
	 * modified date: 2007.09.13 DIT ��ġ��
	 * @param key
	 * @param compVal
	 * @return
	 * @throws EntityObjectException
	 */
	public boolean valueEquals(String key, Object compVal1, Object compVal2, Object compVal3, Object compVal4) throws EntityObjectException {
		return (valueEquals(key, compVal1) || valueEquals(key, compVal2) ||
				valueEquals(key, compVal3) || valueEquals(key, compVal4));
	}

	/** �ش� key���� value�� �д´�. �ش� key�� ������ ����Ʈ�� ���ڷ� ���� "" ���� �����Ѵ�.
	 * @param key��                     String
	 * @param Exception�߻�����         int
	 * @return Object
	 */
	public Object get(String _key, int isThrowEx) {
		
		Object result = null;
		String tmpKey = _key;
		
		try	{
			//Exception�� �߻��ؾ� �ϴ°�?
			if (isThrowEx == YES) {
				result = get(tmpKey);
			}else {
				if (tmpKey == null) {
					result = "";
				}else {
					//tmpKey���� �빮�ڷ� ��ȯ
					tmpKey = tmpKey.toUpperCase();
					//�ش� tmpKey���� HashTable�� �ִ°�?
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

	/** �ش� key���� value�� �д´�. �ش� key�� ������ ����Ʈ������ �����Ѵ�.
	 * @param key��                     String
	 * @param Exception�߻�����          int
	 * @param ����Ʈ��					String
	 * @return Object
	 */
	public Object get(String _key, int isThrowEx, String defaultValue) {
		
		String tmpKey = _key;
		Object obj = null;
		try	{
			//Exception�� �߻��ؾ� �ϴ°�?
			if (isThrowEx == YES) {
				obj = get(tmpKey);
			}else {
				if (tmpKey == null) {
					obj = defaultValue;
				}else {

					//key���� �빮�ڷ� ��ȯ
					tmpKey = tmpKey.toUpperCase();
					//�ش� key���� HashTable�� �ִ°�?
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


	/** EntityObject�� key�� value���� �����Ѵ�
	 *@param key���ڿ�     String
	 *@param value���ڿ�   Object
	 */
	public void put(String _key, Object value)
		throws EntityObjectException {
		String tmpKey = _key;
		try	{
			if (tmpKey == null) {
				//tmpKey���� null�Դϴ�.
				throw new EntityObjectException(ContextIF.MSG_017);
			}
			if (tmpKey.trim().length() == 0) {
				//tmpKey���� �����ϴ�.
				throw new EntityObjectException(ContextIF.MSG_018);
			}
			//tmpKey���� �빮�ڷ� ��ȯ
			tmpKey = tmpKey.toUpperCase();
			//�ؽ����̺� ����
			ht.put(tmpKey, value);
		}
		catch(EntityObjectException eoe) {
			throw eoe;
		}
		catch(Exception e) {
			//�ش� tmpKey�� value ������ ������ �߻��Ͽ����ϴ�.
			throw new EntityObjectException("�ش� tmpKey("+tmpKey+")�� value("+value+") ������ ������ �߻��Ͽ����ϴ�.");
		}
	}

	public void put(String key, int value) throws EntityObjectException {
		put(key, Integer.toString(value));
	}

	public void put(String key, long value) throws EntityObjectException {
		put(key, Long.toString(value));
	}

	/** key�� value���� �����Ѵ�
	 *����° �ƱԸ�Ʈ�� YES�ΰ�� null�� ""�� �����Ѵ�.
	 *@param key���ڿ�     String
	 *@param value���ڿ�   Object
	 *@param ��������null��뿩��  int
	 */
	public void put(String _key, Object _value, int isNull)
		throws EntityObjectException {
		String tmpKey   = _key;
		Object tmpValue = _value;
		try	{
			//null�� ����ϴ°�?
			if (isNull == NO) {
				put(tmpKey, tmpValue);
			}

			if (tmpKey == null) {
				//tmpKey���� null�Դϴ�.
				throw new EntityObjectException(ContextIF.MSG_017);
			}
			if (tmpKey.trim().length() == 0) {
				//tmpKey���� �����ϴ�.
				throw new EntityObjectException(ContextIF.MSG_018);
			}
			//tmpKey���� �빮�ڷ� ��ȯ
			tmpKey = tmpKey.toUpperCase();

			if (tmpValue == null) {
				tmpValue = "";
			}
			else if(isNull == YES && (tmpValue.toString().toUpperCase()).equals("NULL")) {
				tmpValue = "";
			}
			//�ؽ����̺� ����
			ht.put(tmpKey, tmpValue);
		}
		catch(EntityObjectException eoe) {
			throw eoe;
		}
		catch(Exception e) {
			//�ش� tmpKey�� tmpValue ������ ������ �߻��Ͽ����ϴ�.
			throw new EntityObjectException("�ش� tmpKey("+tmpKey+")�� tmpValue("+tmpValue+") ������ ������ �߻��Ͽ����ϴ�.");
		}
	}

	/**�ش� key�� EntityObject���� �����Ѵ�
	 *@param key���ڿ�     String
	 *@return ����
	 */
	public void remove(String _key) throws EntityObjectException {
		Object o = null;
		String tmpkey = _key.toUpperCase();
		o = ht.remove(tmpkey);

		if (o == null) {
			//Debug.println("�ش� key���� �����Ƿ� �����Ҽ� �����ϴ�. : " + key);
			//�ش� key���� �����Ƿ� �����Ҽ� �����ϴ�.
			throw new EntityObjectException(ContextIF.MSG_029);
		}
	}

	/** EntityObject ��ü�� �ش� key�� �ִ°�?
	 *@param key��			String
	 *@return boolean
	 */
	public boolean isExistKey(String _key) {
		String tmpkey = _key.toUpperCase();
		return ht.containsKey(tmpkey);
	}

	/**
	 * key ���� ���θ� üũ
	 * @param keys
	 * @return
	 */
	public void checkExistKey(String keys) throws EntityObjectException {
		if (!isExistKey(keys)) {
			throw new EntityObjectException("�ش� key(" + keys + ")�� value�� �����ϴ�.");
		}
	}

	/**
	 * key ���� ���θ� üũ
	 * @param keys
	 * @return
	 */
	public void checkExistKey(String[] keys) throws EntityObjectException {
		for (int i = 0; i < keys.length; i++) {
			if (!isExistKey(keys[i])) {
				throw new EntityObjectException("�ش� key(" + keys[i] + ")�� value�� �����ϴ�.");
			}
		}
	}

	/** EntityObject ��ü�� ����� �����Ѵ�
	 *@return EntityObject ��ü�� ������
	 */
	public int size() {
		return ht.size();
	}

	/** Entity Object�� ��� �����͸� clear��Ŵ
	 */
	public void clear() {
		ht.clear();
	}

	/** EntityObject�� clone�� ����
	 * @return Object ��ü
	 */
	public synchronized Object clone() {
		EntityObject eoClone;
		try	{
			//1. Clonable�� �̿��ϴ� ���
			//eoClone = new EntityObject();
			//eoClone = (EntityObject)super.clone();

			//2. Serializable�� �̿��ϴ� ���
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

	/**EntityObject ��ü�� ��� key�� value��
	 * EntityObject �迭�� ��ȯ�Ͽ� �����Ѵ�.
	 *@return EntityObject �迭
	 */
	public EntityObject[] getAll() throws EntityObjectException {
		List v = null;
		EntityObject [] eos = null;
		EntityObject eo = null;
		Enumeration keys = null;
		Enumeration values = null;
		try	{
			//�ؽ����̺��� key ����Ʈ�� ���Ѵ�.
			keys = ht.keys();
			//�ؽ����̺��� value ����Ʈ�� ���Ѵ�.
			values = ht.elements();
			//Vector ��ü ����
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

	/**EntityObject�� ��� key�� value�� "key : value"���·�
	 * �ֿܼ� �α׸� ����Ѵ�
	 */
	public void println() throws EntityObjectException {
		EntityObject[] eoList = null;
		try {
			//EntityObject�� ��ü�� EntityObject �迭�� �޴´�
			eoList = getAll();
			for(int i=0; i<eoList.length; i++) {
			}
		}
		catch(Exception e) {
			throw new EntityObjectException(e.toString());
		}
	}

	/**EntityObject�� ��� key�� value�� "key : value"���·�
	 * �ֿܼ� �α׸� ����Ѵ�
	 */
	public void print() throws EntityObjectException {
	}

	/**EntityObject�� ��� key�� value�� "key : value"���·�
	 * �ֿܼ� �α׸� ����Ѵ�
	 */
	public String  getPrintString() throws EntityObjectException {
		EntityObject[] eoList = null;
		StringBuffer buffer = null;
		try {
			//EntityObject�� ��ü�� EntityObject �迭�� �޴´�
			eoList = getAll();

			buffer = new StringBuffer();
			buffer.append("********************EntityObject start*****************");
			buffer.append(System.getProperty("line.separator"));
			buffer.append("[EntityObject(������ = " + eoList.length + ")]");
			buffer.append(System.getProperty("line.separator"));
			for(int i=0; i<eoList.length; i++) {
				buffer.append("#" + i + " ��° �ε��� : ");
				if (eoList[i].getValue().getClass().isArray())	{
					buffer.append("������ = ");
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


	/** Entity Object�� ��� key�� value�� HTML�� ����
	 *@return HTML ���ڿ�
	 */
	public String printHtml() throws EntityObjectException {
		return printHtml("");
	}

	/** Entity Object�� ��� key�� value�� HTML�� ����
	 *@return HTML ���ڿ�
	 */
	public String printHtml(String title) throws EntityObjectException {
		StringBuffer sb = null;
		EntityObject[] eoList = null;
		try	{
			//EntityObject�� ��ü�� EntityObject �迭�� �޴´�
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

	/** key�� �б�
	 *@return key ���ڿ�
	 */
	public String getKey() {
		return this.key;
	}

	/** key�� ����
	 *@param key ���ڿ�
	 */
	private void setKey (String key) {
		this.key = key;
	}

	/** value�� �б�
	 *@return value ���ڿ�
	 */
	public Object getValue() {
		return this.value;
	}

	/** value�� ����
	 *@param value ���ڿ�
	 */
	private void setValue (Object value) {
		this.value = value;
	}

	/** key�� �迭 �б�
	 *@return key ���ڿ� �迭
	 */
	public String[] getKeys() throws EntityObjectException {
		Enumeration keys = null;
		String [] keysStr = null;
		int i = 0;
		try {
			//�ؽ����̺��� key ����Ʈ�� ���Ѵ�.
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
	 * �Ķ���� ���� ���ο� ���� ��� ���Ѵ�.
	 * @param returnEo
	 * @return
	 * @throws EntityObjectException
	 */
	public EntityObject putAll(EntityObject returnEo) throws EntityObjectException {
		Enumeration keys = null;
		Enumeration values = null;
		try	{
			//�ؽ����̺��� key ����Ʈ�� ���Ѵ�.
			keys = ht.keys();
			//�ؽ����̺��� value ����Ʈ�� ���Ѵ�.
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


	/** EntityObject �׽�Ʈ�� �޼ҵ�
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
			String[] b = {"��", "��", "��"};
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
			String b = "��";
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
			eo.put("cc", "����", EntityObject.YES);
			eo.put("dd", "ȣȣ");

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
