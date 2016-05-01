package com.tc.bean;

import java.io.Serializable;

/**
 * 封装message
 * 原因：ObjectInputStream需要传输序列化对象
 * @author 北 纬
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
