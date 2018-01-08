package com.mathhead200.tuil.lexer;


public class GreaterThanOrEqualToToken implements OperatorToken
{
	private GreaterThanOrEqualToToken() {
	}
	
	public static final GreaterThanOrEqualToToken instance = new GreaterThanOrEqualToToken();
	
	public String getLexeme() {
		return ">=";
	}
}
