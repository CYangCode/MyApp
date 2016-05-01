package com.dc.bean;

public class Classmate {
	private String id;
	private String cId;
	private String username;
	private String userAccount;
	private String row;
	private String col;

	public Classmate() {
	}

	public Classmate(String id, String cId, String username,
			String userAccount, String row, String col) {
		super();
		this.id = id;
		this.cId = cId;
		this.username = username;
		this.userAccount = userAccount;
		this.row = row;
		this.col = col;
	}

	public Classmate(String cId, String username, String userAccount,
			String row, String col) {
		super();
		this.cId = cId;
		this.username = username;
		this.userAccount = userAccount;
		this.row = row;
		this.col = col;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

}
