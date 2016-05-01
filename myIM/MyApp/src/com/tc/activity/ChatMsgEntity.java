
package com.tc.activity;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatMsgEntity {
    private String account;

    private String date;

    private String text;

    private boolean isComMeg = true;
    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
		this.account = account;
	}


	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMsgType() {
        return isComMeg;
    }

    public void setMsgType(boolean isComMsg) {
    	isComMeg = isComMsg;
    }

    @Override
	public String toString() {
		return "ChatMsgEntity [name=" + account + ", date=" + date + ", text="
				+ text + ", isComMeg=" + isComMeg + "]";
	}

	public ChatMsgEntity() {
    }

    public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
        super();
        this.account = name;
        this.date = date;
        this.text = text;
        this.isComMeg = isComMsg;
    }
    
    public ChatMsgEntity(String jsonStr) throws JSONException {
    	JSONObject jsonObj = new JSONObject(jsonStr);
    	this.account = jsonObj.getString("username");
    	this.date = jsonObj.getString("time");
    	this.text = jsonObj.getString("mes");
    }
}
