package de.htwg.se.schach.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GuiBoard extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x;
		int y;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				x = i*80;
				y = j*80;
				if((i+j)%2 == 0) {
					g.setColor(Color.WHITE);
				} else  {
					g.setColor(Color.LIGHT_GRAY);
				}
				g.fillRect(x, y, 80, 80);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(80,80);
	}
}
