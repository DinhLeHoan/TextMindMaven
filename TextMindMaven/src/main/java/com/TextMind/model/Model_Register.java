/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class Model_Register {

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Model_Register(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Model_Register() {
	}

	private String userName;
	private String password;

	public JSONObject toJsonObject() {
		try {
			JSONObject json = new JSONObject();
			json.put("userName", userName);
			json.put("password", password);
			return json;
		} catch (JSONException e) {
			return null;
		}
	}
}
