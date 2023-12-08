/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.entity;

/**
 *
 * @author hoanl
 */
public class UserReported {
	private String uIDfrom, uIDto, detail, name;
	private boolean isOnline;

	public UserReported(String uIDfrom, String uIDto, String detail, boolean isOnline) {
		this.uIDfrom = uIDfrom;
		this.uIDto = uIDto;
		this.detail = detail;
		this.isOnline = isOnline;
	}

	public UserReported(String uIDfrom, String uIDto, String detail, String name, boolean isOnline) {
		this.uIDfrom = uIDfrom;
		this.uIDto = uIDto;
		this.detail = detail;
		this.name = name;
		this.isOnline = isOnline;
	}

	public UserReported() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getuIDfrom() {
		return uIDfrom;
	}

	public void setuIDfrom(String uIDfrom) {
		this.uIDfrom = uIDfrom;
	}

	public String getuIDto() {
		return uIDto;
	}

	public void setuIDto(String uIDto) {
		this.uIDto = uIDto;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isIsOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

}
