package com.mathhead200.tuil.lexer;


public class NotEqualToToken implements OperatorToken
{
	private NotEqualToToken() {
	}
	
	public static final NotEqualToToken instance = new NotEqualToToken();
	
	public String getLexeme() {
		return "!=";
	}
}
