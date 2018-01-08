package com.mathhead200.tuil.lexer;


public class EqualToToken implements OperatorToken
{
	private EqualToToken() {
	}
	
	public static final EqualToToken instance = new EqualToToken();
	
	public String getLexeme() {
		return "=";
	}
}
