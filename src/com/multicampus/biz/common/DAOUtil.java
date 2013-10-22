package com.multicampus.biz.common;

import java.util.*;
import java.io.*;

/**<pre>
 *DAO Ŭ�������� SQL ������ UTILITY Ŭ����
 *---------------------------------------------- 
 *update history <br>
 *@since 2012/11/23
 *@author �����
 *</pre>
 */	
public class DAOUtil {		
	/** EntityObject�� �迭�� ���� */
	private EntityObject[] eos = null;
	
	/** EntityObject�� �迭�� �ε��� */
	private int index = 0;
	
	/** DAOUtil ������
	 */
	public DAOUtil(int size) {
		eos = new EntityObject[size];		
		index = 0;
	}	

	/** key�� value���� �����Ѵ�
	 *@param key���ڿ�     String
	 *@param value���ڿ�   Object
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

	/** key�� value���� �����Ѵ�
	 *@param key���ڿ�     String
	 *@param value���ڿ�   Object
	 */
    public void put(String _key, Object value){ 	
		try	{
			//key���� �빮�ڷ� ��ȯ
			String key = _key.toUpperCase();			
			eos[index] = new EntityObject();
			eos[index].put(key, value);
			index++;
		}		
		catch(Exception e) {
			e.printStackTrace();
		}		
	} 	
		
	/**EntityObject �迭���� key�� value�� ���� EntityObject �迭�� ��ȯ�Ͽ� �����Ѵ�.
	 *@return EntityObject �迭
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