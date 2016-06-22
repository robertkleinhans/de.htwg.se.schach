package de.htwg.se.schach.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GuiBoard extends JPanel {
	
	private BufferedImage board;
	
	public GuiBoard() {
		try {
			board = ImageIO.read(new File("img/chess_board.png"));
		} catch (IOException e) {
			System.out.println("[ERROR] could not load chess_board!");
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(board, 0, 0, this);
	}
}
