/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.swing;

import javax.swing.JProgressBar;

/**
 *
 * @author KHOA
 */
public class Progress extends JProgressBar {

	public ProgressType getProgressType() {
		return progressType;
	}

	public void setProgressType(ProgressType progressType) {
		this.progressType = progressType;
		repaint();
	}

	private ProgressType progressType = ProgressType.NONE;

	public Progress() {
		setOpaque(false);
		setUI(new ProgressCircleUI(this));
	}

	public static enum ProgressType {
		NONE, DOWN_FILE, CANCEL, FILE
	}
}
