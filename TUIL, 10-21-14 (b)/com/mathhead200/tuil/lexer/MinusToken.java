package com.mathhead200.tuil.lexer;


public class MinusToken implements OperatorToken
{
	private MinusToken() {
	}
	
	public static final MinusToken instance = new MinusToken();
	
	public String getLexeme() {
		return "-";
	}
}
