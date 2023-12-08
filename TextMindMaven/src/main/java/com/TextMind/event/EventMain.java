/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TextMind.event;

import com.TextMind.entity.User;

/**
 *
 * @author KHOA
 */
public interface EventMain {
	public void showLoading(boolean show);

	public void initChat();

	public void selectUser(User user);

	public void signOut();

}
