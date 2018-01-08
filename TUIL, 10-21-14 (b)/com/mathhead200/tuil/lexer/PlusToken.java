package com.mathhead200.tuil.lexer;


public class PlusToken implements OperatorToken
{
	private PlusToken() {
	}
	
	public static final PlusToken instance = new PlusToken();
	
	public String getLexeme() {
		return "+";
	}
}
