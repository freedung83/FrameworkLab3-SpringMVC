package com.multicampus.biz.common;

import java.util.*;
import java.io.*;

/**<pre>
 *DAO 클래스에서 SQL 쿼리를 UTILITY 클래스
 *---------------------------------------------- 
 *update history <br>
 *@since 2012/11/23
 *@author 김민혁
 *</pre>
 */	
public class DAOUtil {		
	/** EntityObject의 배열을 저장 */
	private EntityObject[] eos = null;
	
	/** EntityObject의 배열의 인덱스 */
	private int index = 0;
	
	/** DAOUtil 생성자
	 */
	public DAOUtil(int size) {
		eos = new EntityObject[size];		
		index = 0;
	}	

	/** key와 value값을 저장한다
	 *@param key문자열     String
	 *@param value문자열   Object
	 */
    public EntityObject get(int index){ 	
		EntityObject eo = null;
		try	{
			eo = eos[index];
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return eo;
	} 

	/** key와 value값을 저장한다
	 *@param key문자열     String
	 *@param value문자열   Object
	 */
    public void put(String _key, Object value){ 	
		try	{
			//key값을 대문자로 변환
			String key = _key.toUpperCase();			
			eos[index] = new EntityObject();
			eos[index].put(key, value);
			index++;
		}		
		catch(Exception e) {
			e.printStackTrace();
		}		
	} 	
		
	/**EntityObject 배열값을 key와 value를 가진 EntityObject 배열로 변환하여 리턴한다.
	 *@return EntityObject 배열
	 */	
	public EntityObject[] getAll() {
		EntityObject[] tmpEos = null;
		EntityObject[] retEos = null;
		try	{
			retEos = new EntityObject[eos.length];
			for(int i=0; i<eos.length; i++) {
				tmpEos = eos[i].getAll();
				retEos[i] = tmpEos[0];
			}
		}
		catch(EntityObjectException eoe) {
		}
		return retEos;		
	}

	public void print() {
		try {
			for(int i=0; i<index; i++) {
				eos[i].print();
			}
		}
		catch(EntityObjectException eoe) {
		}
	}
	
	public int size(){
		return index;
	}

	@Override
	public String toString() {
		
		String str = "";
		
		try {			
			String key = "";
			Object val;
			String[] keys;
			for (int i = 0; i < eos.length; i++) {				
				if( eos[i] != null ){
					keys = eos[i].getKeys();			
					
					for (int j = 0; j < keys.length; j++) {
						key = keys[j];
						val = eos[i].get(key);		
						
						str = str + "[" + key + ":" + val +"], ";  
					}
				}
				
			}
		} catch (EntityObjectException e) {
			throw new RuntimeException(e);
		}
		
		return str;
	}	
	
}