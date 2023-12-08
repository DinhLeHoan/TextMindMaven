/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.Auth;

import com.TextMind.entity.User;

/**
 *
 * @author hoanl
 */
public class Auth {
	public static User user = null;
	public static String uIDCurrentChat = "123";

	public static void clear() {
		Auth.user = null;
	}

	public static boolean isLogin() {
		return Auth.user != null;
	}

	public static boolean isAdmin() {
		return Auth.user.isIsAdmin();
	}
}
