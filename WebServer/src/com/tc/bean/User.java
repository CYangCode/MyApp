package com.tc.bean;

/**
 * �û�
 * 
 * @author �� γ
 *
 */
public class User {
	private int id;
	private String uAccount;
	private String uPassword;
	private String uName;
	private String uPosition;
	/**
	 * ���˼�飨����ǩ����
	 */
	private String uContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getuAccount() {
		return uAccount;
	}

	public void setuAccount(String uAccount) {
		this.uAccount = uAccount;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPosition() {
		return uPosition;
	}

	public void setuPosition(String uSex) {
		this.uPosition = uSex;
	}

	public String getuContent() {
		return uContent;
	}

	public void setuContent(String uContent) {
		this.uContent = uContent;
	}

	public User(int id, String uAccount, String uPassword, String uName,
			String uPos, String uContent) {
		super();
		this.id = id;
		this.uAccount = uAccount;
		this.uPassword = uPassword;
		this.uName = uName;
		this.uPosition = uPos;
		this.uContent = uContent;
	}

	public User(String uAccount, String uPassword, String uName, String uPos,
			String uContent) {
		super();
		this.uAccount = uAccount;
		this.uPassword = uPassword;
		this.uName = uName;
		this.uPosition = uPos;
		this.uContent = uContent;
	}

	public User() {
		super();
	}

	public User(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uAccount=" + uAccount + ", uPassword="
				+ uPassword + ", uName=" + uName + ", uSex=" + uPosition
				+ ", uContent=" + uContent + "]";
	}

	public String toJsonString() {
		return "{'id':'" + id + "', 'useraccount':'" + uAccount
				+ "', 'username':'" + uName + "', 'password':'" + uPassword
				+ "', 'position':'" + uPosition + "', 'content':'" + uContent
				+ "'}";
	}
}
