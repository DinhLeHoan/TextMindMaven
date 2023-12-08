/*
 * $Id: GradientPanel.java,v 1.1 2005/05/25 23:13:24 rbair Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 */

package com.TextMind.Helper;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

public class GradientPanelB extends JPanel {
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		Color gradientStart = new Color(218, 235, 242);// 220, 255, 149);
		Color gradientEnd = new Color(255, 255, 255);// 183, 234, 98);
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint painter = new GradientPaint(0, 0, gradientStart, 0, height, gradientEnd);
		Paint oldPainter = g2.getPaint();
		g2.setPaint(painter);
		g2.fill(g2.getClip());

		gradientStart = new Color(218, 235, 242);
		gradientEnd = new Color(255, 255, 255, 255);

		painter = new GradientPaint(0, 0, gradientEnd, 0, height / 2, gradientStart);
		g2.setPaint(painter);
		g2.fill(g2.getClip());

		painter = new GradientPaint(0, height / 2, gradientStart, 0, height, gradientEnd);
		g2.setPaint(painter);
		g2.fill(g2.getClip());

		g2.setPaint(oldPainter);
	}
}
