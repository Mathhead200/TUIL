package com.mathhead200.tuil;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ScreenBuffer extends JPanel
{
	private JLabel[][] matrix;
	
	private int caretRow = 0;
	private int caretCol = 0;
	private Color highlight = null;
	
	
	public ScreenBuffer(int rows, int cols, int size) {
		super( new GridLayout(rows, cols) );
		
		if( rows <= 0 || cols <= 0 )
			throw new IllegalArgumentException("buffer must be at least 1 by 1");
		
		setOpaque(true);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, size);
		this.matrix = new JLabel[rows][cols];
		for( int row = 0; row < matrix.length; row++ )
			for( int col = 0; col < matrix[0].length; col++ ) {
				matrix[row][col] = new JLabel(" ", JLabel.CENTER);
				matrix[row][col].setFont(font);
				
				matrix[row][col].setOpaque(false);
				matrix[row][col].setForeground( getForeground() );
			}
	}
	
	
	public int getCaretRow() {
		return caretRow;
	}
	
	public int getCaretCol() {
		return caretCol;
	}
	
	public void setCaret(int row, int col) {
		if( row < 0 || col < 0 ||row >= matrix.length || col >= matrix[0].length )
			throw new IndexOutOfBoundsException("illegal caret position: " + row + "," + col);
		caretRow = row;
		caretCol = col;
	}
	
	public Color getHighlight() {
		return highlight;
	}
	
	public void setHighlight(Color highlight) {
		this.highlight = highlight;
	}
	
	
	public void display(String text, int row, int col) {
		if( row < 0 || col < 0 ||row >= matrix.length || col >= matrix[0].length )
			throw new IndexOutOfBoundsException("illegal caret position: " + row + "," + col);
		
		for( int i = 0; i < text.length(); i++ ) {
			if( highlight != null ) {
				matrix[row][col].setOpaque(true);
				matrix[row][col].setBackground(highlight);
			} else {
				matrix[row][col].setOpaque(false);
			}
			matrix[row][col].setForeground( getForeground() );
			matrix[row][col].setText( Character.toString(text.charAt(i)) );
			if( ++col == matrix[0].length ) {
				col = 0;
				if( ++row == matrix.length )
					row = 0;
			}
		}
		setCaret(row, col);
		validate();
	}
	
	public void display(String text) {
		display(text, caretRow, caretCol);
	}
}
