package com.mathhead200.tuil.lexer;


public class LessThanOrEqualToToken implements OperatorToken
{
	private LessThanOrEqualToToken() {
	}
	
	public static final LessThanOrEqualToToken instance = new LessThanOrEqualToToken();
	
	public String getLexeme() {
		return "<=";
	}
}
