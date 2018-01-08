package com.mathhead200.tuil.lexer;


public class CloseParenthesisToken implements Token
{
	private CloseParenthesisToken() {
	}
	
	public static final CloseParenthesisToken instance = new CloseParenthesisToken();
	
	public String getLexeme() {
		return ")";
	}
}
