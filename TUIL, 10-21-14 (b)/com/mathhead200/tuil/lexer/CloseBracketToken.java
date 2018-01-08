package com.mathhead200.tuil.lexer;


public class CloseBracketToken implements Token
{
	private CloseBracketToken() {
	}
	
	public static final CloseBracketToken instance = new CloseBracketToken();
	
	public String getLexeme() {
		return "]";
	}
}
