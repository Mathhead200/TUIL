package com.mathhead200.tuil;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;


public class Tuil
{
	private JFrame frame = new JFrame("TUIL");
	private Map<String, ScreenBuffer> screens = new HashMap<>();
	private Map<String, ComplexRational> numbers = new HashMap<>();
	private Map<String, String> strings = new HashMap<>();
	
	
	public Tuil() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		
		Tuil tuil = new Tuil();
		
		// TODO: stub
		tuil.frame.setVisible(true);
		
	}
}
