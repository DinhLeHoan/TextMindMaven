/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 *
 * @author KHOA
 */
public class ScrollBar extends JScrollBar {

	public ScrollBar() {
		setUI(new ModernScrollBarUI());
		setPreferredSize(new Dimension(5, 5));
		setBackground(new Color(242, 242, 242));
		setUnitIncrement(20);
	}

}
