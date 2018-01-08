package com.mathhead200.tuil.lexer;


public class CloseBraceToken implements Token
{
	private CloseBraceToken() {
	}
	
	public static final CloseBraceToken instance = new CloseBraceToken();
	
	public String getLexeme() {
		return "}";
	}
}
