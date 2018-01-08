package com.mathhead200.tuil.lexer;


public class LessThanToken implements OperatorToken
{
	private LessThanToken() {
	}
	
	public static final LessThanToken instance = new LessThanToken();
	
	public String getLexeme() {
		return "<";
	}
}
