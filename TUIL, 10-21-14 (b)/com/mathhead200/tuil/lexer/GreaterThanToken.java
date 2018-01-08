package com.mathhead200.tuil.lexer;


public class GreaterThanToken implements OperatorToken
{
	private GreaterThanToken() {
	}
	
	public static final GreaterThanToken instance = new GreaterThanToken();
	
	public String getLexeme() {
		return ">";
	}
}
