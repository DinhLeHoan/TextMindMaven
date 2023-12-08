/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.Socket;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 *
 * @author hoanl
 */
public class SocketManager {
	private static Socket socket;

	public static Socket getSocket() {
		if (socket == null) {
			try {
				socket = IO.socket("https://texttomind.onrender.com");
//                  socket = IO.socket("http://localhost:3000/");
				socket.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return socket;
	}
}
