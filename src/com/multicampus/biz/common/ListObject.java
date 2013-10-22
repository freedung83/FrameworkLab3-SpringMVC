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
 *객체들을 저장하는 클래스
 *(참고)Iterator Pattern 사용
 *--------------------------------------
 *update history <br>
 *@since 2012/11/23
 *@author 김민혁
 *</pre>
 */
public class ListObject implements Serializable {
	Vector list = null;
	int currentIndex = 0;

	static final long serialVersionUID = -8814565523139764073L;

	/** ListObject의 생성자
	 */
	public ListObject() {
		list = new Vector();
		currentIndex = -1;
	}

	/** ListObject의 초기화
	 */
	public void init() {
		list = new Vector();
		currentIndex = -1;
	}

	/** ListObject의 지정된 인덱스의 객체를 조회한다.
	 *@param index             int
	 *@return Object 객체
	 */
	public Object get(int index) throws ListObjectException {
		Object o = null;
		try {
			o = list.get(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {
			//index is out of range (index < 0 || index >= size()).
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		return o;
	}

	/**
	 * 번거로운 타입캐스팅을 피하기 위해 추가함
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public EntityObject getEo(int index) throws ListObjectException {
		return (EntityObject)get(index);
	}

	/**
	 * 번거로운 타입캐스팅을 피하기 위해 추가함
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public ListObject getLo(int index) throws ListObjectException {
		return (ListObject)get(index);
	}

	/** ListObject에 저장된 EntityObject의 해당 키가 있는 EntityObject을 리턴한다
	 *@param Key             String
	 *@return EntityObject 객체
	 */
	public EntityObject get(String key) throws ListObjectException {
		EntityObject eo = null;
		EntityObject[] eoList = null;
		boolean flag = false;

		//ListObject의 size만큼 loop
		int size = list.size();
		for(int i=0; i<size; i++) {
			eo = (EntityObject)list.get(i);
			try	{
				eoList = eo.getAll();
			}
			catch(EntityObjectException eoe) {}
			//EntityObject 배열의 길이만큼 loop
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

	/** ListObject의 마지막에 객체 추가
	 *@param element			Object
	 */
	public void add(Object o) throws ListObjectException {
		if (o == null) {
			throw new ListObjectException(ContextIF.MSG_010);
		}
		list.add(o);
	}

	/** ListObject에 추가
	 * @param lo
	 * @throws ListObjectException
	 */
	public void addList(ListObject lo) throws ListObjectException {
		if (null == lo) {
			throw new ListObjectException(ContextIF.MSG_010);
		}
		list.addAll(lo.list);
	}

	/** ListObject의 지정된 위치에 객체 추가
	 *@param index				int
	 *@param element			Object
	 */
	public void add(int index, Object o) throws ListObjectException {
		if (o == null) {
			//ListObject에 저장할 객체가 null입니다.
			throw new ListObjectException(ContextIF.MSG_010);
		}
		try	{
			list.add(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {
			//index is out of range (index < 0 || index >= size()).
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	/** ListObject의 해당 인덱스에 데이터를 저장
	 *@param index				int
	 *@param element			Object
	 */
	public void set(int index, Object o) throws ListObjectException {
		Object tmp = null;
		if (o == null) {
			//ListObject에 저장할 객체가 null입니다.
			throw new ListObjectException("ListObject에 저장할 객체가 null입니다.");
		}

		try	{
			tmp = list.set(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) { //index is out of range (index < 0 || index >= size()).
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	/** ListObject에서 지정된 인덱스의 객체를 제거
	 *@param index				int
	 */
	public void remove(int index) throws ListObjectException {
		try	{
			list.remove(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) { //index is out of range (index < 0 || index >= size()).
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	/** ListObject의 길이를 조회한다.
	 *@return ListObject의 길이
	 */
	public int size() {
		return list.size();
	}

	/** ListObject의 clone을 생성
	 * @return Object 객체
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

	/**ListObject의 내용을 콘솔에 출력한다
	 */
	public void print() throws ListObjectException {
		Object o = null;
		StringBuffer buffer = null;
		try {
			buffer = new StringBuffer();
			buffer.append("====================ListObject start===================");
			buffer.append(System.getProperty("line.separator"));
			int size = list.size();
			buffer.append("[ListObject(전체사이즈 = " + size + ")]");
			buffer.append(System.getProperty("line.separator"));
			for(int i=0; i<size; i++) {
				o = list.get(i);
				buffer.append("@" + i + "번째 인덱스");
				buffer.append(System.getProperty("line.separator"));
				if (o instanceof EntityObject) {
					buffer.append(((EntityObject)o).getPrintString());
				}
				else {
					if (o.getClass().isArray())	{
						buffer.append("사이즈 = ");
						if (o.getClass().getComponentType().isPrimitive()) {
							buffer.append((new ToStringGenerator()).getPrimitiveArrayFieldData(o, ""));
						}
						else {
							buffer.append((new ToStringGenerator()).getNonPrimitiveArrayFieldData(o, ""));
						}
					}
					else {
						buffer.append("타입 = " + o.getClass().getName());
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
	/** ListObject의 첫번째 인덱스의 객체를 리턴
	 *@return Object 객체
	 */
	public Object first() throws ListObjectException {
		if (list.size() == 0) {
			//ListObject 객체의 사이즈가 0입니다.
			throw new ListObjectException(ContextIF.MSG_009);
		}
		currentIndex = 0;
		return list.firstElement();
	}

	/**
	 * 번거로운 타입캐스팅을 피하기 위해 추가함
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public EntityObject firstEo() throws ListObjectException {
		return (EntityObject)first();
	}

	/** ListObject의 마지막 인덱스의 객체를 리턴
	 *@return Object 객체
	 */
	public Object last() throws ListObjectException {
		if (list.size() == 0) {
			//ListObject 객체의 사이즈가 0입니다.
			throw new ListObjectException(ContextIF.MSG_009);
		}
		currentIndex = list.size() - 1;
		return list.lastElement();
	}

	/**
	 * 번거로운 타입캐스팅을 피하기 위해 추가함
	 * @param index
	 * @return
	 * @throws ListObjectException
	 */
	public EntityObject lastEo() throws ListObjectException {
		return (EntityObject)last();
	}

	/** ListObject의 다음 인덱스의 객체를 리턴
	 *@return Object 객체
	 */
	public Object next() throws ListObjectException {
		int tmpIndex = 0;

		if ((currentIndex < 0) || (currentIndex >=list.size())) {
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		tmpIndex = currentIndex;
		currentIndex = currentIndex + 1;
		return list.get(tmpIndex);
	}

	/** ListObject의 전 인덱스의 객체를 리턴
	 *@return Object 객체
	 */
	public Object previous() throws ListObjectException {
		int tmpIndex = 0;

		if ((currentIndex < 0) || (currentIndex >=list.size())) {
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		tmpIndex = currentIndex;
		currentIndex = currentIndex - 1;
		return list.get(tmpIndex);
	}

	/** ListObject의 현재 인덱스의 객체를 리턴
	 *@return Object 객체
	 */
	public Object currentItem() throws ListObjectException {
		if ((currentIndex < 0) || (currentIndex >=list.size())) {
			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
		return list.get(currentIndex);
	}

	/** ListObject에서 현재 인덱스의 객체를 제거
	 */
	public void remove() throws ListObjectException {
		try	{
			list.remove(currentIndex);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) { //index is out of range (index < 0 || index >= size()).

			//지정된 인덱스 범위 에러입니다.
			throw new ListObjectException(ContextIF.MSG_027);
		}
	}

	public void clear() {
		list.clear();
	}

	/** ListObject의 현재의 위치에서 다음의 인덱스가 있는가?
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

	/** ListObject에 저장된 객체들을 Enumeration로 리턴
	 *@return Enumeration
	 */
	public Enumeration elements() {
		return list.elements();
	}

	/** ListObject를 배열로 변환하여 리턴
	 *@return Object배열
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
