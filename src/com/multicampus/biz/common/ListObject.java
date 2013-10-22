package com.multicampus.biz.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**<pre>
 *��ü���� �����ϴ� Ŭ����
 *(����)Iterator Pattern ���
 *--------------------------------------
 *update history <br>
 *@since 2012/11/23
 *@author �����
 *</pre>
 */
public class ListObject implements Serializable {
	Vector list = null;
	int currentIndex = 0;

	static final long serialVersionUID = -8814565523139764073L;

	/** ListObject�� ������
	 */
	public ListObject() {
		list = new Vector();
		currentIndex = -1;
	}

	/** ListObject�� �ʱ�ȭ
	 */
	public void init() {
		list = new Vector();
		currentIndex = -1;
	}

	/** ListObject�� ������ �ε����� ��ü�� ��ȸ�Ѵ�.
	 *@param index             int
	 *@return Object ��ü
	 */
	public Object get(int index) throws ListObjectException {
		Object o = null;
		try {
			o = list.get(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {
			//index is out of range (index < 0 || index >= size()).
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		return o;
	}

	/**
	 * ���ŷο� Ÿ��ĳ������ ���ϱ� ���� �߰���
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public EntityObject getEo(int index) throws ListObjectException {
		return (EntityObject)get(index);
	}

	/**
	 * ���ŷο� Ÿ��ĳ������ ���ϱ� ���� �߰���
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public ListObject getLo(int index) throws ListObjectException {
		return (ListObject)get(index);
	}

	/** ListObject�� ����� EntityObject�� �ش� Ű�� �ִ� EntityObject�� �����Ѵ�
	 *@param Key             String
	 *@return EntityObject ��ü
	 */
	public EntityObject get(String key) throws ListObjectException {
		EntityObject eo = null;
		EntityObject[] eoList = null;
		boolean flag = false;

		//ListObject�� size��ŭ loop
		int size = list.size();
		for(int i=0; i<size; i++) {
			eo = (EntityObject)list.get(i);
			try	{
				eoList = eo.getAll();
			}
			catch(EntityObjectException eoe) {}
			//EntityObject �迭�� ���̸�ŭ loop
			for(int j=0; j<eoList.length; j++) {
				if (eoList[j].getKey().equalsIgnoreCase(key)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

		if (flag) {
			return eo;
		}
		else {
			throw new ListObjectException(ContextIF.MSG_031);
		}
	}

	/** ListObject�� �������� ��ü �߰�
	 *@param element			Object
	 */
	public void add(Object o) throws ListObjectException {
		if (o == null) {
			throw new ListObjectException(ContextIF.MSG_010);
		}
		list.add(o);
	}

	/** ListObject�� �߰�
	 * @param lo
	 * @throws ListObjectException
	 */
	public void addList(ListObject lo) throws ListObjectException {
		if (null == lo) {
			throw new ListObjectException(ContextIF.MSG_010);
		}
		list.addAll(lo.list);
	}

	/** ListObject�� ������ ��ġ�� ��ü �߰�
	 *@param index				int
	 *@param element			Object
	 */
	public void add(int index, Object o) throws ListObjectException {
		if (o == null) {
			//ListObject�� ������ ��ü�� null�Դϴ�.
			throw new ListObjectException(ContextIF.MSG_010);
		}
		try	{
			list.add(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {
			//index is out of range (index < 0 || index >= size()).
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	/** ListObject�� �ش� �ε����� �����͸� ����
	 *@param index				int
	 *@param element			Object
	 */
	public void set(int index, Object o) throws ListObjectException {
		Object tmp = null;
		if (o == null) {
			//ListObject�� ������ ��ü�� null�Դϴ�.
			throw new ListObjectException("ListObject�� ������ ��ü�� null�Դϴ�.");
		}

		try	{
			tmp = list.set(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) { //index is out of range (index < 0 || index >= size()).
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	/** ListObject���� ������ �ε����� ��ü�� ����
	 *@param index				int
	 */
	public void remove(int index) throws ListObjectException {
		try	{
			list.remove(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) { //index is out of range (index < 0 || index >= size()).
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	/** ListObject�� ���̸� ��ȸ�Ѵ�.
	 *@return ListObject�� ����
	 */
	public int size() {
		return list.size();
	}

	/** ListObject�� clone�� ����
	 * @return Object ��ü
	 */
	public synchronized Object clone() {
		ListObject loClone;
		try	{

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			loClone = (ListObject)ois.readObject();
			ois.close();
		}
		catch(Exception e) {
		loClone = null;
		}
		return loClone;
	}

	/**ListObject�� ������ �ֿܼ� ����Ѵ�
	 */
	public void print() throws ListObjectException {
		Object o = null;
		StringBuffer buffer = null;
		try {
			buffer = new StringBuffer();
			buffer.append("====================ListObject start===================");
			buffer.append(System.getProperty("line.separator"));
			int size = list.size();
			buffer.append("[ListObject(��ü������ = " + size + ")]");
			buffer.append(System.getProperty("line.separator"));
			for(int i=0; i<size; i++) {
				o = list.get(i);
				buffer.append("@" + i + "��° �ε���");
				buffer.append(System.getProperty("line.separator"));
				if (o instanceof EntityObject) {
					buffer.append(((EntityObject)o).getPrintString());
				}
				else {
					if (o.getClass().isArray())	{
						buffer.append("������ = ");
						if (o.getClass().getComponentType().isPrimitive()) {
							buffer.append((new ToStringGenerator()).getPrimitiveArrayFieldData(o, ""));
						}
						else {
							buffer.append((new ToStringGenerator()).getNonPrimitiveArrayFieldData(o, ""));
						}
					}
					else {
						buffer.append("Ÿ�� = " + o.getClass().getName());
						buffer.append(System.getProperty("line.separator"));
						buffer.append(o.toString());
					}
				}
				buffer.append(System.getProperty("line.separator"));
				if (i != (size-1)) {
					buffer.append("-------------------------------------------------------");
				}
				buffer.append(System.getProperty("line.separator"));
			}
			buffer.append("====================ListObject end=====================");
		}
		catch(Exception e) {
			throw new ListObjectException(e.toString());
		}
	}


	//***********************************************
	//************ Iterator Pattern *****************
	//***********************************************
	/** ListObject�� ù��° �ε����� ��ü�� ����
	 *@return Object ��ü
	 */
	public Object first() throws ListObjectException {
		if (list.size() == 0) {
			//ListObject ��ü�� ����� 0�Դϴ�.
			throw new ListObjectException(ContextIF.MSG_009);
		}
		currentIndex = 0;
		return list.firstElement();
	}

	/**
	 * ���ŷο� Ÿ��ĳ������ ���ϱ� ���� �߰���
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public EntityObject firstEo() throws ListObjectException {
		return (EntityObject)first();
	}

	/** ListObject�� ������ �ε����� ��ü�� ����
	 *@return Object ��ü
	 */
	public Object last() throws ListObjectException {
		if (list.size() == 0) {
			//ListObject ��ü�� ����� 0�Դϴ�.
			throw new ListObjectException(ContextIF.MSG_009);
		}
		currentIndex = list.size() - 1;
		return list.lastElement();
	}

	/**
	 * ���ŷο� Ÿ��ĳ������ ���ϱ� ���� �߰���
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public EntityObject lastEo() throws ListObjectException {
		return (EntityObject)last();
	}

	/** ListObject�� ���� �ε����� ��ü�� ����
	 *@return Object ��ü
	 */
	public Object next() throws ListObjectException {
		int tmpIndex = 0;

		if ((currentIndex < 0) || (currentIndex >=list.size())) {
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		tmpIndex = currentIndex;
		currentIndex = currentIndex + 1;
		return list.get(tmpIndex);
	}

	/** ListObject�� �� �ε����� ��ü�� ����
	 *@return Object ��ü
	 */
	public Object previous() throws ListObjectException {
		int tmpIndex = 0;

		if ((currentIndex < 0) || (currentIndex >=list.size())) {
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		tmpIndex = currentIndex;
		currentIndex = currentIndex - 1;
		return list.get(tmpIndex);
	}

	/** ListObject�� ���� �ε����� ��ü�� ����
	 *@return Object ��ü
	 */
	public Object currentItem() throws ListObjectException {
		if ((currentIndex < 0) || (currentIndex >=list.size())) {
			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		return list.get(currentIndex);
	}

	/** ListObject���� ���� �ε����� ��ü�� ����
	 */
	public void remove() throws ListObjectException {
		try	{
			list.remove(currentIndex);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) { //index is out of range (index < 0 || index >= size()).

			//������ �ε��� ���� �����Դϴ�.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	public void clear() {
		list.clear();
	}

	/** ListObject�� ������ ��ġ���� ������ �ε����� �ִ°�?
	 *@return boolean
	 */
	public boolean hasMore() {
		int tmpIndex = 0;
		boolean result = false;

		if ((currentIndex == -1) && (list.size() > 0)) {
			currentIndex = 0;
		}

		tmpIndex = currentIndex + 1;
		try {
			list.get(tmpIndex);
			result = true;
		}
		catch(ArrayIndexOutOfBoundsException aoe) {
			result = false;
		}
		
		return result;
	}

	/** ListObject�� ����� ��ü���� Enumeration�� ����
	 *@return Enumeration
	 */
	public Enumeration elements() {
		return list.elements();
	}

	/** ListObject�� �迭�� ��ȯ�Ͽ� ����
	 *@return Object�迭
	 */
	public Object[] toArray() {
		return list.toArray();
	}

	/**
	 * 2008-11-08 DongbuCNI Chiyol Kim
	 * @param arr
	 * @return
	 */
	public Object[] toArray(Object[] arr) {
		return list.toArray(arr);
	}
	
	@Override
	public String toString() {
		
		String str = "======= Params =========\n";		
		int size = list.size();
		for (int i = 0; list != null && i < size; i++) {
			str = str + list.get(i) + "\n";			
		}
		str += "======================";
		return str;
	}

	/**
	 * 
	 * @param daoPramList
	 */
	public void setParam(List<DaoParam> daoParamList) {
		
		if( daoParamList != null ) {
			int daoParamListSize = daoParamList.size();
			DAOUtil typeDaou = new DAOUtil(daoParamListSize);
			DAOUtil dataDaou = new DAOUtil(daoParamListSize);
			
			DaoParam daoParam;
			for (int i = 0; i < daoParamListSize; i++) {
				
				daoParam = daoParamList.get(i);
				typeDaou.put(daoParam.getKey(), daoParam.getSqlType());  
				dataDaou.put(daoParam.getKey(), daoParam.getValue());
			}	
			
			try {
				add(typeDaou);
				add(dataDaou);
			} catch (ListObjectException e) {
				throw new RuntimeException(e);
			}
	       
		}
				
	}
}
