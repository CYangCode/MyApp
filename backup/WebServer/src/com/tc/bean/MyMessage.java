package com.tc.bean;

import java.io.Serializable;

/**
 * ��װmessage
 * ԭ��ObjectInputStream��Ҫ�������л�����
 * @author �� γ
 *
 */
public class MyMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MyMessage(String message) {
		super();
		this.message = message;
	}
	public MyMessage() {
		super();
	}
	
	

}
