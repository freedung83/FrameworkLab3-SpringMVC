package com.multicampus.biz.common;

import java.lang.reflect.Field;

/**<pre>
 *toString() �޼ҵ带 �ڵ����� �����ϴ� ��ƿ Ŭ����
 *-------------------------------------------------
 *update history <br>
 *@since 2012/11/23
 *@author �����
 *</pre>
 */	
public class ToStringGenerator {
	/** ������ */
	public ToStringGenerator() {}	
	
	/** toString() �޼ҵ带 �ڵ����� �����Ѵ�. */
	public String generateToString(Object parent) {
		Field[] fields = parent.getClass().getDeclaredFields();
		Class cSuper = parent.getClass().getSuperclass();
		
		StringBuffer buffer = new StringBuffer();
		String result = "";
		if (fields.length == 0 && cSuper == Object.class) {
			result =  "�� Ŭ������ �Ӽ��� ������ ���� �ʽ��ϴ�.";
		}else {	
			
			buffer.append(parent.getClass().getName());
			buffer.append(" [");
			buffer.append(cSuper.getName());
			buffer.append("]" + System.getProperty("line.separator"));
			if (cSuper != Object.class) {
				buffer.append(super.toString()); //�θ�Ŭ������ toString�� �߰�
			}
			
			int dimensions = 0;
			for (int i = 0; i < fields.length; i++)	{
				if (fields[i].getType().isPrimitive()) {
					buffer.append(System.getProperty("line.separator") + getPrimitiveFieldData(fields[i], parent));
				}
				else {
					if (fields[i].getType().isArray()) {
						dimensions = calculateDimensions(fields[i].getType());
						if (dimensions > 1) {
							buffer.append(System.getProperty("line.separator") + fields[i].getName() + " :: ������ �迭�� �������� �ʽ��ϴ�!");
						}
						else
							if (fields[i].getType().getComponentType().isPrimitive()) {
								buffer.append(System.getProperty("line.separator") + getPrimitiveArrayFieldData(fields[i], parent));
							}
							else {
								buffer.append(System.getProperty("line.separator") + getNonPrimitiveArrayFieldData(fields[i], parent));
							}
					}
					else {
						buffer.append(System.getProperty("line.separator") + getNonPrimitiveFieldData(fields[i], parent));
					}
				}
			} // End of For Loop
		}
		result = buffer.toString();
		return result;
	}

	public int calculateDimensions(Class _c){
		Class c = _c;
		int dims = 0;
		boolean array = c.isArray();
		/*
		while (array)	{
			dims++;
			c = c.getComponentType();
		}
		*/
		return dims;
	}

	public String getNonPrimitiveArrayFieldData(Object value, String name) {
		StringBuffer output = new StringBuffer();
		Object[] values = null;
		values = (Object[]) value;
		output.append(values.length);
		for (int i = 0; i < values.length; i++)	{
			output.append(System.getProperty("line.separator"));
			output.append(name + "[" + i + "] = " + values[i].toString());
		}		
		return output.toString();
	}

	public String getNonPrimitiveArrayFieldData(Field field, Object parent) {
		StringBuffer output = new StringBuffer();
		output.append(field.getName() + "[] :: ���� = ");
		try	{
			Object value = field.get(parent);
			output.append(getNonPrimitiveArrayFieldData(value, field.getName()));			
		}
		catch (IllegalAccessException exception) {
			output.append("*������ �źεǾ����ϴ�*");
		}
		return output.toString();
	}

	public String getNonPrimitiveFieldData(Field field, Object parent) {
		StringBuffer output = new StringBuffer();
		output.append(field.getName() + " = ");
		try	{
			Object value = field.get(parent);			
			output.append(value.toString());
		}
		catch (IllegalAccessException exception){
			output.append("*������ �źεǾ����ϴ�*");
		}
		return output.toString();
	}

	public String getPrimitiveArrayFieldData(Object value, String name){
		StringBuffer output = new StringBuffer();
		int aLength = 0;
		Object[] values = null;
		char primitiveTypeIdentifier = value.getClass().getComponentType().toString().charAt(0);
		char extraPrimitiveTypeIdentifier = value.getClass().getComponentType().toString().charAt(1);
		
		switch (primitiveTypeIdentifier){
			case 'f' :
				float[] fValues = (float[]) value;
				aLength = fValues.length;
				values = new Object[aLength];
				for (int i = 0; i < fValues.length; i++)
					values[i] = new Float(fValues[i]);
				break;
			case 'i' :
				int[] iValues = (int[]) value;
				aLength = iValues.length;
				values = new Object[aLength];
				for (int i = 0; i < iValues.length; i++)
					values[i] = new Integer(iValues[i]);
				break;
			case 'd' :
				double[] dValues = (double[]) value;
				aLength = dValues.length;
				values = new Object[aLength];
				for (int i = 0; i < dValues.length; i++)
					values[i] = new Double(dValues[i]);
				break;
			case 's' :
				short[] sValues = (short[]) value;
				aLength = sValues.length;
				values = new Object[aLength];
				for (int i = 0; i < sValues.length; i++)
					values[i] = new Short(sValues[i]);
				break;
			case 'b' :
				if (extraPrimitiveTypeIdentifier == 'o') {
					boolean[] bValues = (boolean[]) value;
					aLength = bValues.length;
					values = new Object[aLength];
					for (int i = 0; i < bValues.length; i++)
						values[i] = new Boolean(bValues[i]);
				}
				else {
					byte[] bValues = (byte[]) value;
					aLength = bValues.length;
					values = new Object[aLength];
					for (int i = 0; i < bValues.length; i++)
						values[i] = new Byte(bValues[i]);
				}
				break;
			case 'l' :
				long[] lValues = (long[]) value;
				aLength = lValues.length;
				values = new Object[aLength];
				for (int i = 0; i < lValues.length; i++)
					values[i] = new Long(lValues[i]);
				break;
			case 'c' :
				char[] cValues = (char[]) value;
				aLength = cValues.length;
				values = new Object[aLength];
				for (int i = 0; i < cValues.length; i++)
					values[i] = new Character(cValues[i]);
				break;
			default :
				output.append("*��ȿ�������� ���� Ÿ���Դϴ�*");
				break;
		} // End of Switch

		output.append(aLength);
		
		if( values != null ){
			int length = values.length;
			for (int i = 0; i < length; i++)	{
				output.append(System.getProperty("line.separator"));
				output.append(name + "[" + i + "] = " + values[i].toString());
			}
		}
		
		return output.toString();
	}

	public String getPrimitiveArrayFieldData(Field field, Object parent){
		StringBuffer output = new StringBuffer();
		output.append(field.getName() + "[] :: ���� = ");
		try	{
			Object value = field.get(parent);
			output.append(getPrimitiveArrayFieldData(value,field.getName()));
		}
		catch (IllegalAccessException exception) {
			output.append("*������ �źεǾ����ϴ�*");
		}
		return output.toString();
	}


	public String getPrimitiveFieldData(Field field, Object parent){
		StringBuffer output = new StringBuffer();
		output.append(field.getName() + " = ");
		try	{
			Object value = field.get(parent);
			output.append(value.toString());
		}
		catch (IllegalAccessException exception){
			output.append("*������ �źεǾ����ϴ�*");
		}
		return output.toString();
	}
}
