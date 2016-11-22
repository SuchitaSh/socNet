package com.socnet.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class ObjectToStringSerializer {

	public static String serialize(Serializable object){
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream objectOut = null;
		String result = null;
		
		try {
			byteOut = new ByteArrayOutputStream();
			objectOut = new ObjectOutputStream(byteOut);

			objectOut.writeObject(object);
			objectOut.flush();
			result = byteOut.toString("ISO-8859-1");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (objectOut != null) {
					objectOut.close();
				}
				if (byteOut != null) {
					byteOut.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;	
	}
	
	public static Object deserialize(String object){
		
		ByteArrayInputStream byteIn = null;
		ObjectInputStream objectIn = null;
		Object result = null;
		try {
			byte[] bytes = object.getBytes("ISO-8859-1"); 
			byteIn = new ByteArrayInputStream(bytes);
			objectIn = new ObjectInputStream(byteIn);
			result = objectIn.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if (objectIn != null) {
					objectIn.close();
				}
				if (byteIn != null) {
					byteIn.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	} 
	
	
}
