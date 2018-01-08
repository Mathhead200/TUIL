package com.mathhead200.tuil.lexer;


public class DivideToken implements OperatorToken
{
	private DivideToken() {
	}
	
	public static final DivideToken instance = new DivideToken();
	
	public String getLexeme() {
		return "/";
	}
}
